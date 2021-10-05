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
import com.example.wordgame.model_layer.Learn;
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
public final class LearnDao_Impl implements LearnDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Learn> __insertionAdapterOfLearn;

  private final EntityDeletionOrUpdateAdapter<Learn> __updateAdapterOfLearn;

  public LearnDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLearn = new EntityInsertionAdapter<Learn>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Learn` (`learnId`,`level`,`section`,`content`,`instruction`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Learn value) {
        stmt.bindLong(1, value.learnId);
        stmt.bindLong(2, value.getLevel());
        if (value.getSection() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSection());
        }
        if (value.getContent() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getContent());
        }
        if (value.getInstruction() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getInstruction());
        }
      }
    };
    this.__updateAdapterOfLearn = new EntityDeletionOrUpdateAdapter<Learn>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Learn` SET `learnId` = ?,`level` = ?,`section` = ?,`content` = ?,`instruction` = ? WHERE `learnId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Learn value) {
        stmt.bindLong(1, value.learnId);
        stmt.bindLong(2, value.getLevel());
        if (value.getSection() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSection());
        }
        if (value.getContent() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getContent());
        }
        if (value.getInstruction() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getInstruction());
        }
        stmt.bindLong(6, value.learnId);
      }
    };
  }

  @Override
  public void insert(final Learn learningMaterial) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfLearn.insert(learningMaterial);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Learn LearningMaterial) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfLearn.handle(LearningMaterial);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Learn>> getAll() {
    final String _sql = "SELECT * FROM Learn";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Learn"}, false, new Callable<List<Learn>>() {
      @Override
      public List<Learn> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLearnId = CursorUtil.getColumnIndexOrThrow(_cursor, "learnId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfSection = CursorUtil.getColumnIndexOrThrow(_cursor, "section");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfInstruction = CursorUtil.getColumnIndexOrThrow(_cursor, "instruction");
          final List<Learn> _result = new ArrayList<Learn>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Learn _item;
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpSection;
            if (_cursor.isNull(_cursorIndexOfSection)) {
              _tmpSection = null;
            } else {
              _tmpSection = _cursor.getString(_cursorIndexOfSection);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpInstruction;
            if (_cursor.isNull(_cursorIndexOfInstruction)) {
              _tmpInstruction = null;
            } else {
              _tmpInstruction = _cursor.getString(_cursorIndexOfInstruction);
            }
            _item = new Learn(_tmpLevel,_tmpSection,_tmpContent,_tmpInstruction);
            _item.learnId = _cursor.getInt(_cursorIndexOfLearnId);
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
  public LiveData<List<Learn>> level1() {
    final String _sql = "SELECT * FROM LEARN WHERE  level ==1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"LEARN"}, false, new Callable<List<Learn>>() {
      @Override
      public List<Learn> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLearnId = CursorUtil.getColumnIndexOrThrow(_cursor, "learnId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfSection = CursorUtil.getColumnIndexOrThrow(_cursor, "section");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfInstruction = CursorUtil.getColumnIndexOrThrow(_cursor, "instruction");
          final List<Learn> _result = new ArrayList<Learn>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Learn _item;
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpSection;
            if (_cursor.isNull(_cursorIndexOfSection)) {
              _tmpSection = null;
            } else {
              _tmpSection = _cursor.getString(_cursorIndexOfSection);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpInstruction;
            if (_cursor.isNull(_cursorIndexOfInstruction)) {
              _tmpInstruction = null;
            } else {
              _tmpInstruction = _cursor.getString(_cursorIndexOfInstruction);
            }
            _item = new Learn(_tmpLevel,_tmpSection,_tmpContent,_tmpInstruction);
            _item.learnId = _cursor.getInt(_cursorIndexOfLearnId);
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
  public LiveData<List<Learn>> level2() {
    final String _sql = "SELECT * FROM LEARN WHERE level ==1 or level==2 ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"LEARN"}, false, new Callable<List<Learn>>() {
      @Override
      public List<Learn> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLearnId = CursorUtil.getColumnIndexOrThrow(_cursor, "learnId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfSection = CursorUtil.getColumnIndexOrThrow(_cursor, "section");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfInstruction = CursorUtil.getColumnIndexOrThrow(_cursor, "instruction");
          final List<Learn> _result = new ArrayList<Learn>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Learn _item;
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpSection;
            if (_cursor.isNull(_cursorIndexOfSection)) {
              _tmpSection = null;
            } else {
              _tmpSection = _cursor.getString(_cursorIndexOfSection);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpInstruction;
            if (_cursor.isNull(_cursorIndexOfInstruction)) {
              _tmpInstruction = null;
            } else {
              _tmpInstruction = _cursor.getString(_cursorIndexOfInstruction);
            }
            _item = new Learn(_tmpLevel,_tmpSection,_tmpContent,_tmpInstruction);
            _item.learnId = _cursor.getInt(_cursorIndexOfLearnId);
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
  public LiveData<List<Learn>> level3() {
    final String _sql = "SELECT * FROM LEARN WHERE level ==1 or level ==2 or level ==3";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"LEARN"}, false, new Callable<List<Learn>>() {
      @Override
      public List<Learn> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLearnId = CursorUtil.getColumnIndexOrThrow(_cursor, "learnId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfSection = CursorUtil.getColumnIndexOrThrow(_cursor, "section");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfInstruction = CursorUtil.getColumnIndexOrThrow(_cursor, "instruction");
          final List<Learn> _result = new ArrayList<Learn>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Learn _item;
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpSection;
            if (_cursor.isNull(_cursorIndexOfSection)) {
              _tmpSection = null;
            } else {
              _tmpSection = _cursor.getString(_cursorIndexOfSection);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpInstruction;
            if (_cursor.isNull(_cursorIndexOfInstruction)) {
              _tmpInstruction = null;
            } else {
              _tmpInstruction = _cursor.getString(_cursorIndexOfInstruction);
            }
            _item = new Learn(_tmpLevel,_tmpSection,_tmpContent,_tmpInstruction);
            _item.learnId = _cursor.getInt(_cursorIndexOfLearnId);
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
  public Learn findByName(final String first, final String last) {
    final String _sql = "SELECT * FROM Learn WHERE level LIKE ? AND section LIKE ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (first == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, first);
    }
    _argIndex = 2;
    if (last == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, last);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfLearnId = CursorUtil.getColumnIndexOrThrow(_cursor, "learnId");
      final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
      final int _cursorIndexOfSection = CursorUtil.getColumnIndexOrThrow(_cursor, "section");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfInstruction = CursorUtil.getColumnIndexOrThrow(_cursor, "instruction");
      final Learn _result;
      if(_cursor.moveToFirst()) {
        final int _tmpLevel;
        _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
        final String _tmpSection;
        if (_cursor.isNull(_cursorIndexOfSection)) {
          _tmpSection = null;
        } else {
          _tmpSection = _cursor.getString(_cursorIndexOfSection);
        }
        final String _tmpContent;
        if (_cursor.isNull(_cursorIndexOfContent)) {
          _tmpContent = null;
        } else {
          _tmpContent = _cursor.getString(_cursorIndexOfContent);
        }
        final String _tmpInstruction;
        if (_cursor.isNull(_cursorIndexOfInstruction)) {
          _tmpInstruction = null;
        } else {
          _tmpInstruction = _cursor.getString(_cursorIndexOfInstruction);
        }
        _result = new Learn(_tmpLevel,_tmpSection,_tmpContent,_tmpInstruction);
        _result.learnId = _cursor.getInt(_cursorIndexOfLearnId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
