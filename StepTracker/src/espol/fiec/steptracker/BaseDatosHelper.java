 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class BaseDatosHelper extends SQLiteOpenHelper {
    private static BaseDatosHelper sInstance;
 
    // Logcat tag
    private static final String LOG = BaseDatosHelper.class.getName();
 
    // Database Version
    private static final int DATABASE_VERSION = 2;
 
    // Database Name
    private static final String DATABASE_NAME = "stepTrackerBD";
 
    // Table Names
    private static final String TABLE_POSICIONES = "posiciones";
    private static final String TABLE_DIAS = "dias";
    private static final String TABLE_DIAS_POSICIONES = "dias_posiciones";
 
    // Columnas comunes
    private static final String KEY_ID = "id";
 
    // Columnas tabla POSICIONES
    private static final String KEY_LAT = "latitud";
    private static final String KEY_LON = "longitud";
 
    // Columnas tabla DIAS
    private static final String KEY_FECHA = "fecha";
    private static final String KEY_DISTANCIA = "distanciatotal";
    private static final String KEY_TIEMPO = "tiempototal";
 
    // Columnas relación POSICIONES-DIAS
    private static final String KEY_ID_POS = "id_pos";
    private static final String KEY_ID_DIA = "id_dia";
 
    // Crear tablas
    // Query creación tabla Posiciones
    private static final String CREATE_TABLE_POSICIONES = "CREATE TABLE "
            + TABLE_POSICIONES + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LAT
            + " TEXT," + KEY_POS + " TEXT" + ")";
 
    // Query creación tabla Dias
    private static final String CREATE_TABLE_DIAS = "CREATE TABLE " + TABLE_DIAS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FECHA + " TEXT,"
            + KEY_DISTANCIA + " REAL," + KEY_TIEMPO + " REAL" + ")";
 
    // Query creación tabla Dias-Posiciones
    private static final String CREATE_TABLE_DIAS_POSICIONES = "CREATE TABLE "
            + TABLE_DIAS_POSICIONES + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_ID_POS + " INTEGER," + KEY_ID_DIA + " INTEGER" + ")";

    public static BaseDatosHelper getInstance(Context context){
        if(sInstance==null){
           sInstance=new BaseDatosHelper(context.getApplicationContext());
        }
        return sInstance;
    }
 
    private BaseDatosHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
 
        // creating required tables
        db.execSQL(CREATE_TABLE_POSICIONES);
        db.execSQL(CREATE_TABLE_DIAS);
        db.execSQL(CREATE_TABLE_DIAS_POSICIONES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSICIONES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIAS_POSICIONES);
 
        // create new tables
        onCreate(db);
    }
 
    // ------------------------ Métodos tabla "Dias" ----------------//

     /**
     * Crear Día inicial
     */
    public Dia crearDia() {
        SQLiteDatabase db = this.getWritableDatabase();
        Dia dia = new Dia(getDateTime());
        ContentValues values = new ContentValues();
        values.put(KEY_FECHA, dia.getFecha());
        values.put(KEY_DISTANCIA, dia.getDistancia());
        values.put(KEY_TIEMPO, dia.getTiempo());

        // insertar linea
        long dia_id = db.insert(TABLE_DIAS, null, values);
        dia.setId(dia_id);
        return dia;
    }

     /**
     * obtener un dia por su fecha
     * */
    public Dia getDia(String fecha) {
        Dia d = new Dia();
        String[] args = new String[]{String.valueOf(fecha)};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_DIAS, null, KEY_FECHA + " = ?" , args, null, null, null);
 
        if (c.moveToFirst()) {
            // solo toma el primer valor encontrado
                d.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                d.setFecha(c.getString((c.getColumnIndex(KEY_FECHA))));
                d.setDistancia(c.getDouble((c.getColumnIndex(KEY_DISTANCIA))));
                d.setTiempo(c.getDouble((c.getColumnIndex(KEY_TIEMPO)))); 
        }
 
        return d;
    }

     /**
     * Actualizar Dia
     */
    public int actualizarDia(Dia dia) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = new String[]{String.valueOf(dia.getId())};

        ContentValues values = new ContentValues();
        values.put(KEY_DISTANCIA, dia.getDistancia());
        values.put(KEY_TIEMPO, dia.getTiempo());

        // actualizando
        return db.update(TABLE_DIAS, values, KEY_ID + " = ?", args);
    }


    // ------------------------ Métodos tabla "Posiciones" ----------------//
 
     /**
     * Crear Posiciones
     */
    public long crearPosicion(double longitud, double latitud) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_LAT, latitud);
        values.put(KEY_LON, longitud);
 
        // insert row
        long posicion_id = db.insert(TABLE_POSICIONES, null, values);
 
        return posicion_id;
    }

     /**
     * obtener todas las posiciones de un dia
     * */
    public List<Posicion> getTodasPosicionesDia(Dia dia) {
        List<Posicion> posiciones = new ArrayList<Posicion>();
        long dia_id=dia.getId();
 
        String selectQuery = "SELECT  * FROM " + TABLE_POSICIONES + " pos, "
                + TABLE_DIAS + " d, " + TABLE_DIAS_POSICIONES + " dp WHERE dp."
                + KEY_ID_DIA + " = '" + dia_id + "' AND pos." + KEY_ID + " = "
                + "dp." + KEY_ID_POS;
 
        Log.e(LOG, selectQuery);
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
 
        if (c.moveToFirst()) {
            do {
                Posicion pos = new Posicion();
                pos.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                pos.setLatitud((c.getString(c.getColumnIndex(KEY_LAT))));
                pos.setLongitud(c.getString(c.getColumnIndex(KEY_LON)));
 
                // agregar a la lista de posiciones
                posiciones.add(pos);
            } while (c.moveToNext());
        }
 
        return posiciones;
    }

    // ------------------------ Métodos tabla "Dias-Posiciones" ----------------//
 
     /**

     * Crear Dia-Posiciones

     */
    public long crearDiaPosicion(long dia_id, long posicion_id) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_ID_DIA, dia_id);
        values.put(KEY_ID_POS, posicion_id);
 
        // insert row
        long diaposicion_id = db.insert(TABLE_DIAS_POSICIONES, null, values);
 
        return diaposicion_id;
    }


// posible forma de las funciones para eliminar si se va a implementar el delete


    /**
     * Deleting a todo
     */
/*    public void deleteToDo(long tado_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TODO, KEY_ID + " = ?",
                new String[] { String.valueOf(tado_id) });
    }*/

    /**
     * Deleting a tag
     */
/*    public void deleteTag(Tag tag, boolean should_delete_all_tag_todos) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        // before deleting tag
        // check if todos under this tag should also be deleted
        if (should_delete_all_tag_todos) {
            // get all todos under this tag
            List<Todo> allTagToDos = getAllToDosByTag(tag.getTagName());
 
            // delete all todos
            for (Todo todo : allTagToDos) {
                // delete todo
                deleteToDo(todo.getId());
            }
        }
 
        // now delete the tag
        db.delete(TABLE_TAG, KEY_ID + " = ?",
                new String[] { String.valueOf(tag.getId()) });
    }*/
 
    // cerrar base de datos
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
 
    /**
     * datetime actual, solo el día sin hora
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}

