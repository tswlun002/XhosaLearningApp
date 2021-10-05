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
import com.example.wordgame.model_layer.TrueFalseGame;
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
public final class TrueFalseDao_Impl implements TrueFalseDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TrueFalseGame> __insertionAdapterOfTrueFalseGame;

  private final EntityDeletionOrUpdateAdapter<TrueFalseGame> __updateAdapterOfTrueFalseGame;

  public TrueFalseDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTrueFalseGame = new EntityInsertionAdapter<TrueFalseGame>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `TrueOfFalse` (`trueFalseId`,`level`,`question`,`figures`,`answers`,`instructions`,`totalMarks`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TrueFalseGame value) {
        stmt.bindLong(1, value.trueFalseId);
        stmt.bindLong(2, value.getLevel());
        if (value.getQuestion() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getQuestion());
        }
        if (value.getFigures() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFigures());
        }
        if (value.getAnswers() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAnswers());
        }
        if (value.getInstructions() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getInstructions());
        }
        stmt.bindLong(7, value.getTotalMarks());
      }
    };
    this.__updateAdapterOfTrueFalseGame = new EntityDeletionOrUpdateAdapter<TrueFalseGame>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `TrueOfFalse` SET `trueFalseId` = ?,`level` = ?,`question` = ?,`figures` = ?,`answers` = ?,`instructions` = ?,`totalMarks` = ? WHERE `trueFalseId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TrueFalseGame value) {
        stmt.bindLong(1, value.trueFalseId);
        stmt.bindLong(2, value.getLevel());
        if (value.getQuestion() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getQuestion());
        }
        if (value.getFigures() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFigures());
        }
        if (value.getAnswers() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAnswers());
        }
        if (value.getInstructions() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getInstructions());
        }
        stmt.bindLong(7, value.getTotalMarks());
        stmt.bindLong(8, value.trueFalseId);
      }
    };
  }

  @Override
  public void insert(final TrueFalseGame activity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTrueFalseGame.insert(activity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final TrueFalseGame activity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTrueFalseGame.handle(activity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<TrueFalseGame>> getAll() {
    final String _sql = "SELECT * FROM TrueOfFalse";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"TrueOfFalse"}, false, new Callable<List<TrueFalseGame>>() {
      @Override
      public List<TrueFalseGame> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTrueFalseId = CursorUtil.getColumnIndexOrThrow(_cursor, "trueFalseId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfQuestion = CursorUtil.getColumnIndexOrThrow(_cursor, "question");
          final int _cursorIndexOfFigures = CursorUtil.getColumnIndexOrThrow(_cursor, "figures");
          final int _cursorIndexOfAnswers = CursorUtil.getColumnIndexOrThrow(_cursor, "answers");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final List<TrueFalseGame> _result = new ArrayList<TrueFalseGame>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TrueFalseGame _item;
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpQuestion;
            if (_cursor.isNull(_cursorIndexOfQuestion)) {
              _tmpQuestion = null;
            } else {
              _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
            }
            final String _tmpFigures;
            if (_cursor.isNull(_cursorIndexOfFigures)) {
              _tmpFigures = null;
            } else {
              _tmpFigures = _cursor.getString(_cursorIndexOfFigures);
            }
            final String _tmpAnswers;
            if (_cursor.isNull(_cursorIndexOfAnswers)) {
              _tmpAnswers = null;
            } else {
              _tmpAnswers = _cursor.getString(_cursorIndexOfAnswers);
            }
            final String _tmpInstructions;
            if (_cursor.isNull(_cursorIndexOfInstructions)) {
              _tmpInstructions = null;
            } else {
              _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            }
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            _item = new TrueFalseGame(_tmpLevel,_tmpQuestion,_tmpFigures,_tmpAnswers,_tmpInstructions,_tmpTotalMarks);
            _item.trueFalseId = _cursor.getInt(_cursorIndexOfTrueFalseId);
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
  public LiveData<List<TrueFalseGame>> loadLevelOne() {
    final String _sql = "SELECT * FROM TrueOfFalse WHERE level =1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"TrueOfFalse"}, false, new Callable<List<TrueFalseGame>>() {
      @Override
      public List<TrueFalseGame> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTrueFalseId = CursorUtil.getColumnIndexOrThrow(_cursor, "trueFalseId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfQuestion = CursorUtil.getColumnIndexOrThrow(_cursor, "question");
          final int _cursorIndexOfFigures = CursorUtil.getColumnIndexOrThrow(_cursor, "figures");
          final int _cursorIndexOfAnswers = CursorUtil.getColumnIndexOrThrow(_cursor, "answers");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final List<TrueFalseGame> _result = new ArrayList<TrueFalseGame>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TrueFalseGame _item;
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpQuestion;
            if (_cursor.isNull(_cursorIndexOfQuestion)) {
              _tmpQuestion = null;
            } else {
              _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
            }
            final String _tmpFigures;
            if (_cursor.isNull(_cursorIndexOfFigures)) {
              _tmpFigures = null;
            } else {
              _tmpFigures = _cursor.getString(_cursorIndexOfFigures);
            }
            final String _tmpAnswers;
            if (_cursor.isNull(_cursorIndexOfAnswers)) {
              _tmpAnswers = null;
            } else {
              _tmpAnswers = _cursor.getString(_cursorIndexOfAnswers);
            }
            final String _tmpInstructions;
            if (_cursor.isNull(_cursorIndexOfInstructions)) {
              _tmpInstructions = null;
            } else {
              _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            }
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            _item = new TrueFalseGame(_tmpLevel,_tmpQuestion,_tmpFigures,_tmpAnswers,_tmpInstructions,_tmpTotalMarks);
            _item.trueFalseId = _cursor.getInt(_cursorIndexOfTrueFalseId);
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
  public LiveData<List<TrueFalseGame>> loadLevelTwo() {
    final String _sql = "SELECT * FROM TrueOfFalse WHERE level =2";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"TrueOfFalse"}, false, new Callable<List<TrueFalseGame>>() {
      @Override
      public List<TrueFalseGame> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTrueFalseId = CursorUtil.getColumnIndexOrThrow(_cursor, "trueFalseId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfQuestion = CursorUtil.getColumnIndexOrThrow(_cursor, "question");
          final int _cursorIndexOfFigures = CursorUtil.getColumnIndexOrThrow(_cursor, "figures");
          final int _cursorIndexOfAnswers = CursorUtil.getColumnIndexOrThrow(_cursor, "answers");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final List<TrueFalseGame> _result = new ArrayList<TrueFalseGame>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TrueFalseGame _item;
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpQuestion;
            if (_cursor.isNull(_cursorIndexOfQuestion)) {
              _tmpQuestion = null;
            } else {
              _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
            }
            final String _tmpFigures;
            if (_cursor.isNull(_cursorIndexOfFigures)) {
              _tmpFigures = null;
            } else {
              _tmpFigures = _cursor.getString(_cursorIndexOfFigures);
            }
            final String _tmpAnswers;
            if (_cursor.isNull(_cursorIndexOfAnswers)) {
              _tmpAnswers = null;
            } else {
              _tmpAnswers = _cursor.getString(_cursorIndexOfAnswers);
            }
            final String _tmpInstructions;
            if (_cursor.isNull(_cursorIndexOfInstructions)) {
              _tmpInstructions = null;
            } else {
              _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            }
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            _item = new TrueFalseGame(_tmpLevel,_tmpQuestion,_tmpFigures,_tmpAnswers,_tmpInstructions,_tmpTotalMarks);
            _item.trueFalseId = _cursor.getInt(_cursorIndexOfTrueFalseId);
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
  public LiveData<List<TrueFalseGame>> loadLevelThree() {
    final String _sql = "SELECT * FROM TrueOfFalse WHERE level =3";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"TrueOfFalse"}, false, new Callable<List<TrueFalseGame>>() {
      @Override
      public List<TrueFalseGame> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTrueFalseId = CursorUtil.getColumnIndexOrThrow(_cursor, "trueFalseId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfQuestion = CursorUtil.getColumnIndexOrThrow(_cursor, "question");
          final int _cursorIndexOfFigures = CursorUtil.getColumnIndexOrThrow(_cursor, "figures");
          final int _cursorIndexOfAnswers = CursorUtil.getColumnIndexOrThrow(_cursor, "answers");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final List<TrueFalseGame> _result = new ArrayList<TrueFalseGame>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TrueFalseGame _item;
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpQuestion;
            if (_cursor.isNull(_cursorIndexOfQuestion)) {
              _tmpQuestion = null;
            } else {
              _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
            }
            final String _tmpFigures;
            if (_cursor.isNull(_cursorIndexOfFigures)) {
              _tmpFigures = null;
            } else {
              _tmpFigures = _cursor.getString(_cursorIndexOfFigures);
            }
            final String _tmpAnswers;
            if (_cursor.isNull(_cursorIndexOfAnswers)) {
              _tmpAnswers = null;
            } else {
              _tmpAnswers = _cursor.getString(_cursorIndexOfAnswers);
            }
            final String _tmpInstructions;
            if (_cursor.isNull(_cursorIndexOfInstructions)) {
              _tmpInstructions = null;
            } else {
              _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            }
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            _item = new TrueFalseGame(_tmpLevel,_tmpQuestion,_tmpFigures,_tmpAnswers,_tmpInstructions,_tmpTotalMarks);
            _item.trueFalseId = _cursor.getInt(_cursorIndexOfTrueFalseId);
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
