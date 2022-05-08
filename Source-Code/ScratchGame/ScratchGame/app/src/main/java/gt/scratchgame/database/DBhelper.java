package gt.scratchgame.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import gt.scratchgame.base.Session;
import gt.scratchgame.bean.BeanPlayerScore;

import static gt.scratchgame.database.Table.ScoreTable.*;


public class DBhelper< database> extends SQLiteOpenHelper{

	private static DBhelper dbhelper;
	private SQLiteDatabase database;
	private Context mContext;
    private Session session = Session.getInstance();
	
	private final static String DB_NAME = "scratchgame.db";
	private final static int DB_VERSIN = 1;
	
	private final static String TABLE_NAME = Table.ScoreTable.class.getSimpleName();

	/*Create Tabel*/
	private String createAddtionTable()
	{
		return "create table " + TABLE_NAME
				+ "(" 
				+ id.print() + " primary key, "
                + categoryId.print() + " , "
				+ player_name.print() + " , "
                + player_score.print() + " "
				+ ");";
	}


    /*Create DBhelper singleton instance*/
	public static DBhelper getInstance(Context context) {
        if (dbhelper == null) {
            dbhelper = new DBhelper(context, DB_NAME, null, DB_VERSIN);
        }
        return dbhelper;
    }
	private DBhelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    	db.execSQL(createAddtionTable());
    }
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
     	onCreate(db);
	}

    /*Open Database for read write operation*/
	public void open() {
        database = dbhelper.getWritableDatabase();
    }
    /*Close Database*/
	public void close()
	{
		if(database != null)
		{database.close();}
	}

	public long addData(int category, String player_name, float player_score)
	{
		ContentValues velues = getContentVelues(category, player_name, player_score);
		return database.insert(TABLE_NAME, null, velues);
	}

    private ContentValues getContentVelues(int category, String playerName, float playerScore) {
		// TODO Auto-generated method stub
		ContentValues contentValues = new ContentValues();
        contentValues.put(categoryId.name(),category);
		contentValues.put(player_name.name(), playerName);
        contentValues.put(player_score.name(), playerScore);
		return contentValues;
	}
	
	public List<BeanPlayerScore> getUserData()
	{
		List<BeanPlayerScore> list = new ArrayList<BeanPlayerScore>();
		Cursor cursor = null;
		cursor = database.query(TABLE_NAME, getColList(), null, null, null, null, player_score.name() + " DESC");
		if(cursor.moveToFirst())
		{
			do
			{
				list.add(getData(cursor));
			}while(cursor.moveToNext());
		}
        cursor.close();
		return list;
	}




	private BeanPlayerScore getData(Cursor cursor) {
		// TODO Auto-generated method stub
		BeanPlayerScore beanPlayerScore = new BeanPlayerScore();
		
		beanPlayerScore.id = cursor.getInt(cursor.getColumnIndex(id.name()));
        beanPlayerScore.categoryId = cursor.getInt(cursor.getColumnIndex(categoryId.name()));
        beanPlayerScore.playerName = cursor.getString(cursor.getColumnIndex(player_name.name()));
        beanPlayerScore.playerScore = cursor.getFloat(cursor.getColumnIndex(player_score.name()));
		return beanPlayerScore;
	}

	private String[] getColList() {
		// TODO Auto-generated method stub
		return new String[]{id.name(), categoryId.name(), player_name.name(), player_score.name()};
	}

}
