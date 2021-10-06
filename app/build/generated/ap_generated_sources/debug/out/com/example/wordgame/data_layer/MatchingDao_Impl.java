package com.example.wordgame.data_layer;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.wordgame.model_layer.Matching;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MatchingDao_Impl implements MatchingDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Matching> __insertionAdapterOfMatching;

  private final EntityDeletionOrUpdateAdapter<Matching> __updateAdapterOfMatching;

  public MatchingDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMatching = new EntityInsertionAdapter<Matching>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Matching` (`matchingId`,`level`,`tittle`,`instructions`,`questions`,`answers`,`totalMarks`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Matching value) {
        stmt.bindLong(1, value.matchingId);
        stmt.bindLong(2, value.getLevel());
        if (value.getTittle() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTittle());
        }
        if (value.getInstructions() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getInstructions());
        }
        if (value.getQuestions() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getQuestions());
        }
        if (value.getAnswers() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAnswers());
        }
        stmt.bindLong(7, value.getTotalMarks());
      }
    };
    this.__updateAdapterOfMatching = new EntityDeletionOrUpdateAdapter<Matching>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Matching` SET `matchingId` = ?,`level` = ?,`tittle` = ?,`instructions` = ?,`questions` = ?,`answers` = ?,`totalMarks` = ? WHERE `matchingId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Matching value) {
        stmt.bindLong(1, value.matchingId);
        stmt.bindLong(2, value.getLevel());
        if (value.getTittle() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTittle());
        }
        if (value.getInstructions() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getInstructions());
        }
        if (value.getQuestions() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getQuestions());
        }
        if (value.getAnswers() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAnswers());
        }
        stmt.bindLong(7, value.getTotalMarks());
        stmt.bindLong(8, value.matchingId);
      }
    };
  }

  @Override
  public void insert(final Matching activity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMatching.insert(activity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Matching activity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfMatching.handle(activity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Matching>> getAll() {
    final String _sql = "SELECT * FROM MATCHING where level=1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"MATCHING"}, false, new Callable<List<Matching>>() {
      @Override
      public List<Matching> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMatchingId = CursorUtil.getColumnIndexOrThrow(_cursor, "matchingId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfTittle = CursorUtil.getColumnIndexOrThrow(_cursor, "tittle");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfQuestions = CursorUtil.getColumnIndexOrThrow(_cursor, "questions");
          final int _cursorIndexOfAnswers = CursorUtil.getColumnIndexOrThrow(_cursor, "answers");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final List<Matching> _result = new ArrayList<Matching>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Matching _item;
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpTittle;
            if (_cursor.isNull(_cursorIndexOfTittle)) {
              _tmpTittle = null;
            } else {
              _tmpTittle = _cursor.getString(_cursorIndexOfTittle);
            }
            final String _tmpInstructions;
            if (_cursor.isNull(_cursorIndexOfInstructions)) {
              _tmpInstructions = null;
            } else {
              _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            }
            final String _tmpQuestions;
            if (_cursor.isNull(_cursorIndexOfQuestions)) {
              _tmpQuestions = null;
            } else {
              _tmpQuestions = _cursor.getString(_cursorIndexOfQuestions);
            }
            final String _tmpAnswers;
            if (_cursor.isNull(_cursorIndexOfAnswers)) {
              _tmpAnswers = null;
            } else {
              _tmpAnswers = _cursor.getString(_cursorIndexOfAnswers);
            }
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            _item = new Matching(_tmpLevel,_tmpQuestions,_tmpAnswers,_tmpTittle,_tmpInstructions,_tmpTotalMarks);
            _item.matchingId = _cursor.getInt(_cursorIndexOfMatchingId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Matching>> loadLevelOne() {
    final String _sql = "SELECT * FROM Matching WHERE level =1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Matching"}, false, new Callable<List<Matching>>() {
      @Override
      public List<Matching> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMatchingId = CursorUtil.getColumnIndexOrThrow(_cursor, "matchingId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfTittle = CursorUtil.getColumnIndexOrThrow(_cursor, "tittle");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfQuestions = CursorUtil.getColumnIndexOrThrow(_cursor, "questions");
          final int _cursorIndexOfAnswers = CursorUtil.getColumnIndexOrThrow(_cursor, "answers");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final List<Matching> _result = new ArrayList<Matching>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Matching _item;
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpTittle;
            if (_cursor.isNull(_cursorIndexOfTittle)) {
              _tmpTittle = null;
            } else {
              _tmpTittle = _cursor.getString(_cursorIndexOfTittle);
            }
            final String _tmpInstructions;
            if (_cursor.isNull(_cursorIndexOfInstructions)) {
              _tmpInstructions = null;
            } else {
              _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            }
            final String _tmpQuestions;
            if (_cursor.isNull(_cursorIndexOfQuestions)) {
              _tmpQuestions = null;
            } else {
              _tmpQuestions = _cursor.getString(_cursorIndexOfQuestions);
            }
            final String _tmpAnswers;
            if (_cursor.isNull(_cursorIndexOfAnswers)) {
              _tmpAnswers = null;
            } else {
              _tmpAnswers = _cursor.getString(_cursorIndexOfAnswers);
            }
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            _item = new Matching(_tmpLevel,_tmpQuestions,_tmpAnswers,_tmpTittle,_tmpInstructions,_tmpTotalMarks);
            _item.matchingId = _cursor.getInt(_cursorIndexOfMatchingId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Matching>> loadLevelTwo() {
    final String _sql = "SELECT * FROM Matching WHERE level =2";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Matching"}, false, new Callable<List<Matching>>() {
      @Override
      public List<Matching> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMatchingId = CursorUtil.getColumnIndexOrThrow(_cursor, "matchingId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfTittle = CursorUtil.getColumnIndexOrThrow(_cursor, "tittle");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfQuestions = CursorUtil.getColumnIndexOrThrow(_cursor, "questions");
          final int _cursorIndexOfAnswers = CursorUtil.getColumnIndexOrThrow(_cursor, "answers");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final List<Matching> _result = new ArrayList<Matching>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Matching _item;
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpTittle;
            if (_cursor.isNull(_cursorIndexOfTittle)) {
              _tmpTittle = null;
            } else {
              _tmpTittle = _cursor.getString(_cursorIndexOfTittle);
            }
            final String _tmpInstructions;
            if (_cursor.isNull(_cursorIndexOfInstructions)) {
              _tmpInstructions = null;
            } else {
              _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            }
            final String _tmpQuestions;
            if (_cursor.isNull(_cursorIndexOfQuestions)) {
              _tmpQuestions = null;
            } else {
              _tmpQuestions = _cursor.getString(_cursorIndexOfQuestions);
            }
            final String _tmpAnswers;
            if (_cursor.isNull(_cursorIndexOfAnswers)) {
              _tmpAnswers = null;
            } else {
              _tmpAnswers = _cursor.getString(_cursorIndexOfAnswers);
            }
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            _item = new Matching(_tmpLevel,_tmpQuestions,_tmpAnswers,_tmpTittle,_tmpInstructions,_tmpTotalMarks);
            _item.matchingId = _cursor.getInt(_cursorIndexOfMatchingId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Matching>> loadLevelThree() {
    final String _sql = "SELECT * FROM Matching WHERE level =3";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Matching"}, false, new Callable<List<Matching>>() {
      @Override
      public List<Matching> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMatchingId = CursorUtil.getColumnIndexOrThrow(_cursor, "matchingId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfTittle = CursorUtil.getColumnIndexOrThrow(_cursor, "tittle");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfQuestions = CursorUtil.getColumnIndexOrThrow(_cursor, "questions");
          final int _cursorIndexOfAnswers = CursorUtil.getColumnIndexOrThrow(_cursor, "answers");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final List<Matching> _result = new ArrayList<Matching>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Matching _item;
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpTittle;
            if (_cursor.isNull(_cursorIndexOfTittle)) {
              _tmpTittle = null;
            } else {
              _tmpTittle = _cursor.getString(_cursorIndexOfTittle);
            }
            final String _tmpInstructions;
            if (_cursor.isNull(_cursorIndexOfInstructions)) {
              _tmpInstructions = null;
            } else {
              _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            }
            final String _tmpQuestions;
            if (_cursor.isNull(_cursorIndexOfQuestions)) {
              _tmpQuestions = null;
            } else {
              _tmpQuestions = _cursor.getString(_cursorIndexOfQuestions);
            }
            final String _tmpAnswers;
            if (_cursor.isNull(_cursorIndexOfAnswers)) {
              _tmpAnswers = null;
            } else {
              _tmpAnswers = _cursor.getString(_cursorIndexOfAnswers);
            }
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            _item = new Matching(_tmpLevel,_tmpQuestions,_tmpAnswers,_tmpTittle,_tmpInstructions,_tmpTotalMarks);
            _item.matchingId = _cursor.getInt(_cursorIndexOfMatchingId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
