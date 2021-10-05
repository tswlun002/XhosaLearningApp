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
import com.example.wordgame.model_layer.TranslationGame;
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
public final class TranslationDao_Impl implements TranslationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TranslationGame> __insertionAdapterOfTranslationGame;

  private final EntityDeletionOrUpdateAdapter<TranslationGame> __updateAdapterOfTranslationGame;

  public TranslationDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTranslationGame = new EntityInsertionAdapter<TranslationGame>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `TranslationGame` (`translationId`,`hints`,`hintCounter`,`instructions`,`totalMarks`,`level`,`question`,`answers`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TranslationGame value) {
        stmt.bindLong(1, value.translationId);
        if (value.getHints() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getHints());
        }
        stmt.bindLong(3, value.getHintCounter());
        if (value.getInstructions() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getInstructions());
        }
        stmt.bindLong(5, value.getTotalMarks());
        stmt.bindLong(6, value.getLevel());
        if (value.getQuestion() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getQuestion());
        }
        if (value.getAnswers() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getAnswers());
        }
      }
    };
    this.__updateAdapterOfTranslationGame = new EntityDeletionOrUpdateAdapter<TranslationGame>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `TranslationGame` SET `translationId` = ?,`hints` = ?,`hintCounter` = ?,`instructions` = ?,`totalMarks` = ?,`level` = ?,`question` = ?,`answers` = ? WHERE `translationId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TranslationGame value) {
        stmt.bindLong(1, value.translationId);
        if (value.getHints() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getHints());
        }
        stmt.bindLong(3, value.getHintCounter());
        if (value.getInstructions() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getInstructions());
        }
        stmt.bindLong(5, value.getTotalMarks());
        stmt.bindLong(6, value.getLevel());
        if (value.getQuestion() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getQuestion());
        }
        if (value.getAnswers() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getAnswers());
        }
        stmt.bindLong(9, value.translationId);
      }
    };
  }

  @Override
  public void insert(final TranslationGame activity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTranslationGame.insert(activity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final TranslationGame activity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTranslationGame.handle(activity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<TranslationGame>> getAll() {
    final String _sql = "SELECT * FROM TranslationGame";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"TranslationGame"}, false, new Callable<List<TranslationGame>>() {
      @Override
      public List<TranslationGame> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTranslationId = CursorUtil.getColumnIndexOrThrow(_cursor, "translationId");
          final int _cursorIndexOfHints = CursorUtil.getColumnIndexOrThrow(_cursor, "hints");
          final int _cursorIndexOfHintCounter = CursorUtil.getColumnIndexOrThrow(_cursor, "hintCounter");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfQuestion = CursorUtil.getColumnIndexOrThrow(_cursor, "question");
          final int _cursorIndexOfAnswers = CursorUtil.getColumnIndexOrThrow(_cursor, "answers");
          final List<TranslationGame> _result = new ArrayList<TranslationGame>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TranslationGame _item;
            final String _tmpHints;
            if (_cursor.isNull(_cursorIndexOfHints)) {
              _tmpHints = null;
            } else {
              _tmpHints = _cursor.getString(_cursorIndexOfHints);
            }
            final int _tmpHintCounter;
            _tmpHintCounter = _cursor.getInt(_cursorIndexOfHintCounter);
            final String _tmpInstructions;
            if (_cursor.isNull(_cursorIndexOfInstructions)) {
              _tmpInstructions = null;
            } else {
              _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            }
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpQuestion;
            if (_cursor.isNull(_cursorIndexOfQuestion)) {
              _tmpQuestion = null;
            } else {
              _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
            }
            final String _tmpAnswers;
            if (_cursor.isNull(_cursorIndexOfAnswers)) {
              _tmpAnswers = null;
            } else {
              _tmpAnswers = _cursor.getString(_cursorIndexOfAnswers);
            }
            _item = new TranslationGame(_tmpLevel,_tmpQuestion,_tmpAnswers,_tmpHints,_tmpHintCounter,_tmpInstructions,_tmpTotalMarks);
            _item.translationId = _cursor.getInt(_cursorIndexOfTranslationId);
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
  public LiveData<List<TranslationGame>> level1() {
    final String _sql = "SELECT * FROM TranslationGame WHERE  level ==1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"TranslationGame"}, false, new Callable<List<TranslationGame>>() {
      @Override
      public List<TranslationGame> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTranslationId = CursorUtil.getColumnIndexOrThrow(_cursor, "translationId");
          final int _cursorIndexOfHints = CursorUtil.getColumnIndexOrThrow(_cursor, "hints");
          final int _cursorIndexOfHintCounter = CursorUtil.getColumnIndexOrThrow(_cursor, "hintCounter");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfQuestion = CursorUtil.getColumnIndexOrThrow(_cursor, "question");
          final int _cursorIndexOfAnswers = CursorUtil.getColumnIndexOrThrow(_cursor, "answers");
          final List<TranslationGame> _result = new ArrayList<TranslationGame>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TranslationGame _item;
            final String _tmpHints;
            if (_cursor.isNull(_cursorIndexOfHints)) {
              _tmpHints = null;
            } else {
              _tmpHints = _cursor.getString(_cursorIndexOfHints);
            }
            final int _tmpHintCounter;
            _tmpHintCounter = _cursor.getInt(_cursorIndexOfHintCounter);
            final String _tmpInstructions;
            if (_cursor.isNull(_cursorIndexOfInstructions)) {
              _tmpInstructions = null;
            } else {
              _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            }
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpQuestion;
            if (_cursor.isNull(_cursorIndexOfQuestion)) {
              _tmpQuestion = null;
            } else {
              _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
            }
            final String _tmpAnswers;
            if (_cursor.isNull(_cursorIndexOfAnswers)) {
              _tmpAnswers = null;
            } else {
              _tmpAnswers = _cursor.getString(_cursorIndexOfAnswers);
            }
            _item = new TranslationGame(_tmpLevel,_tmpQuestion,_tmpAnswers,_tmpHints,_tmpHintCounter,_tmpInstructions,_tmpTotalMarks);
            _item.translationId = _cursor.getInt(_cursorIndexOfTranslationId);
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
  public LiveData<List<TranslationGame>> level2() {
    final String _sql = "SELECT * FROM TranslationGame WHERE level ==2";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"TranslationGame"}, false, new Callable<List<TranslationGame>>() {
      @Override
      public List<TranslationGame> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTranslationId = CursorUtil.getColumnIndexOrThrow(_cursor, "translationId");
          final int _cursorIndexOfHints = CursorUtil.getColumnIndexOrThrow(_cursor, "hints");
          final int _cursorIndexOfHintCounter = CursorUtil.getColumnIndexOrThrow(_cursor, "hintCounter");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfQuestion = CursorUtil.getColumnIndexOrThrow(_cursor, "question");
          final int _cursorIndexOfAnswers = CursorUtil.getColumnIndexOrThrow(_cursor, "answers");
          final List<TranslationGame> _result = new ArrayList<TranslationGame>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TranslationGame _item;
            final String _tmpHints;
            if (_cursor.isNull(_cursorIndexOfHints)) {
              _tmpHints = null;
            } else {
              _tmpHints = _cursor.getString(_cursorIndexOfHints);
            }
            final int _tmpHintCounter;
            _tmpHintCounter = _cursor.getInt(_cursorIndexOfHintCounter);
            final String _tmpInstructions;
            if (_cursor.isNull(_cursorIndexOfInstructions)) {
              _tmpInstructions = null;
            } else {
              _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            }
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpQuestion;
            if (_cursor.isNull(_cursorIndexOfQuestion)) {
              _tmpQuestion = null;
            } else {
              _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
            }
            final String _tmpAnswers;
            if (_cursor.isNull(_cursorIndexOfAnswers)) {
              _tmpAnswers = null;
            } else {
              _tmpAnswers = _cursor.getString(_cursorIndexOfAnswers);
            }
            _item = new TranslationGame(_tmpLevel,_tmpQuestion,_tmpAnswers,_tmpHints,_tmpHintCounter,_tmpInstructions,_tmpTotalMarks);
            _item.translationId = _cursor.getInt(_cursorIndexOfTranslationId);
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
  public LiveData<List<TranslationGame>> level3() {
    final String _sql = "SELECT * FROM TranslationGame WHERE level ==3";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"TranslationGame"}, false, new Callable<List<TranslationGame>>() {
      @Override
      public List<TranslationGame> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTranslationId = CursorUtil.getColumnIndexOrThrow(_cursor, "translationId");
          final int _cursorIndexOfHints = CursorUtil.getColumnIndexOrThrow(_cursor, "hints");
          final int _cursorIndexOfHintCounter = CursorUtil.getColumnIndexOrThrow(_cursor, "hintCounter");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfQuestion = CursorUtil.getColumnIndexOrThrow(_cursor, "question");
          final int _cursorIndexOfAnswers = CursorUtil.getColumnIndexOrThrow(_cursor, "answers");
          final List<TranslationGame> _result = new ArrayList<TranslationGame>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TranslationGame _item;
            final String _tmpHints;
            if (_cursor.isNull(_cursorIndexOfHints)) {
              _tmpHints = null;
            } else {
              _tmpHints = _cursor.getString(_cursorIndexOfHints);
            }
            final int _tmpHintCounter;
            _tmpHintCounter = _cursor.getInt(_cursorIndexOfHintCounter);
            final String _tmpInstructions;
            if (_cursor.isNull(_cursorIndexOfInstructions)) {
              _tmpInstructions = null;
            } else {
              _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            }
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpQuestion;
            if (_cursor.isNull(_cursorIndexOfQuestion)) {
              _tmpQuestion = null;
            } else {
              _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
            }
            final String _tmpAnswers;
            if (_cursor.isNull(_cursorIndexOfAnswers)) {
              _tmpAnswers = null;
            } else {
              _tmpAnswers = _cursor.getString(_cursorIndexOfAnswers);
            }
            _item = new TranslationGame(_tmpLevel,_tmpQuestion,_tmpAnswers,_tmpHints,_tmpHintCounter,_tmpInstructions,_tmpTotalMarks);
            _item.translationId = _cursor.getInt(_cursorIndexOfTranslationId);
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
