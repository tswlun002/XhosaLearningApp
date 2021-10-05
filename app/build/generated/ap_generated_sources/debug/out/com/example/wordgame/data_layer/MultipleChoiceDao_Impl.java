package com.example.wordgame.data_layer;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.wordgame.model_layer.MultipleChoice;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MultipleChoiceDao_Impl implements MultipleChoiceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MultipleChoice> __insertionAdapterOfMultipleChoice;

  private final EntityDeletionOrUpdateAdapter<MultipleChoice> __updateAdapterOfMultipleChoice;

  public MultipleChoiceDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMultipleChoice = new EntityInsertionAdapter<MultipleChoice>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `MultipleChoice` (`multipleId`,`level`,`question`,`choiceOne`,`choiceTwo`,`choiceThree`,`choiceFour`,`answer`,`instructions`,`totalMarks`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MultipleChoice value) {
        stmt.bindLong(1, value.multipleId);
        stmt.bindLong(2, value.getLevel());
        if (value.getQuestion() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getQuestion());
        }
        if (value.getChoiceOne() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getChoiceOne());
        }
        if (value.getChoiceTwo() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getChoiceTwo());
        }
        if (value.getChoiceThree() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getChoiceThree());
        }
        if (value.getChoiceFour() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getChoiceFour());
        }
        if (value.getAnswer() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getAnswer());
        }
        if (value.getInstructions() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getInstructions());
        }
        stmt.bindLong(10, value.getTotalMarks());
      }
    };
    this.__updateAdapterOfMultipleChoice = new EntityDeletionOrUpdateAdapter<MultipleChoice>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `MultipleChoice` SET `multipleId` = ?,`level` = ?,`question` = ?,`choiceOne` = ?,`choiceTwo` = ?,`choiceThree` = ?,`choiceFour` = ?,`answer` = ?,`instructions` = ?,`totalMarks` = ? WHERE `multipleId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MultipleChoice value) {
        stmt.bindLong(1, value.multipleId);
        stmt.bindLong(2, value.getLevel());
        if (value.getQuestion() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getQuestion());
        }
        if (value.getChoiceOne() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getChoiceOne());
        }
        if (value.getChoiceTwo() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getChoiceTwo());
        }
        if (value.getChoiceThree() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getChoiceThree());
        }
        if (value.getChoiceFour() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getChoiceFour());
        }
        if (value.getAnswer() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getAnswer());
        }
        if (value.getInstructions() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getInstructions());
        }
        stmt.bindLong(10, value.getTotalMarks());
        stmt.bindLong(11, value.multipleId);
      }
    };
  }

  @Override
  public void insert(final MultipleChoice activity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMultipleChoice.insert(activity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final MultipleChoice activity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfMultipleChoice.handle(activity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<MultipleChoice>> getAll() {
    final String _sql = "SELECT * FROM MultipleChoice";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"MultipleChoice"}, false, new Callable<List<MultipleChoice>>() {
      @Override
      public List<MultipleChoice> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMultipleId = CursorUtil.getColumnIndexOrThrow(_cursor, "multipleId");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfQuestion = CursorUtil.getColumnIndexOrThrow(_cursor, "question");
          final int _cursorIndexOfChoiceOne = CursorUtil.getColumnIndexOrThrow(_cursor, "choiceOne");
          final int _cursorIndexOfChoiceTwo = CursorUtil.getColumnIndexOrThrow(_cursor, "choiceTwo");
          final int _cursorIndexOfChoiceThree = CursorUtil.getColumnIndexOrThrow(_cursor, "choiceThree");
          final int _cursorIndexOfChoiceFour = CursorUtil.getColumnIndexOrThrow(_cursor, "choiceFour");
          final int _cursorIndexOfAnswer = CursorUtil.getColumnIndexOrThrow(_cursor, "answer");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final List<MultipleChoice> _result = new ArrayList<MultipleChoice>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MultipleChoice _item;
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpQuestion;
            if (_cursor.isNull(_cursorIndexOfQuestion)) {
              _tmpQuestion = null;
            } else {
              _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
            }
            final String _tmpChoiceOne;
            if (_cursor.isNull(_cursorIndexOfChoiceOne)) {
              _tmpChoiceOne = null;
            } else {
              _tmpChoiceOne = _cursor.getString(_cursorIndexOfChoiceOne);
            }
            final String _tmpChoiceTwo;
            if (_cursor.isNull(_cursorIndexOfChoiceTwo)) {
              _tmpChoiceTwo = null;
            } else {
              _tmpChoiceTwo = _cursor.getString(_cursorIndexOfChoiceTwo);
            }
            final String _tmpChoiceThree;
            if (_cursor.isNull(_cursorIndexOfChoiceThree)) {
              _tmpChoiceThree = null;
            } else {
              _tmpChoiceThree = _cursor.getString(_cursorIndexOfChoiceThree);
            }
            final String _tmpChoiceFour;
            if (_cursor.isNull(_cursorIndexOfChoiceFour)) {
              _tmpChoiceFour = null;
            } else {
              _tmpChoiceFour = _cursor.getString(_cursorIndexOfChoiceFour);
            }
            final String _tmpAnswer;
            if (_cursor.isNull(_cursorIndexOfAnswer)) {
              _tmpAnswer = null;
            } else {
              _tmpAnswer = _cursor.getString(_cursorIndexOfAnswer);
            }
            final String _tmpInstructions;
            if (_cursor.isNull(_cursorIndexOfInstructions)) {
              _tmpInstructions = null;
            } else {
              _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            }
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            _item = new MultipleChoice(_tmpLevel,_tmpQuestion,_tmpChoiceOne,_tmpChoiceTwo,_tmpChoiceThree,_tmpChoiceFour,_tmpInstructions,_tmpAnswer,_tmpTotalMarks);
            _item.multipleId = _cursor.getInt(_cursorIndexOfMultipleId);
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
  public LiveData<List<MultipleChoice>> loadLevelOne(final int[] userIds) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM Matching WHERE level =1");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    return __db.getInvalidationTracker().createLiveData(new String[]{"Matching"}, false, new Callable<List<MultipleChoice>>() {
      @Override
      public List<MultipleChoice> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final List<MultipleChoice> _result = new ArrayList<MultipleChoice>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MultipleChoice _item;
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpInstructions;
            if (_cursor.isNull(_cursorIndexOfInstructions)) {
              _tmpInstructions = null;
            } else {
              _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            }
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            _item = new MultipleChoice(_tmpLevel,null,null,null,null,null,_tmpInstructions,null,_tmpTotalMarks);
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
  public LiveData<List<MultipleChoice>> loadLevelTwo(final int[] userIds) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM Matching WHERE level =2");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    return __db.getInvalidationTracker().createLiveData(new String[]{"Matching"}, false, new Callable<List<MultipleChoice>>() {
      @Override
      public List<MultipleChoice> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final List<MultipleChoice> _result = new ArrayList<MultipleChoice>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MultipleChoice _item;
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpInstructions;
            if (_cursor.isNull(_cursorIndexOfInstructions)) {
              _tmpInstructions = null;
            } else {
              _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            }
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            _item = new MultipleChoice(_tmpLevel,null,null,null,null,null,_tmpInstructions,null,_tmpTotalMarks);
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
  public LiveData<List<MultipleChoice>> loadLevelThree(final int[] userIds) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM Matching WHERE level =3");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    return __db.getInvalidationTracker().createLiveData(new String[]{"Matching"}, false, new Callable<List<MultipleChoice>>() {
      @Override
      public List<MultipleChoice> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "instructions");
          final int _cursorIndexOfTotalMarks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalMarks");
          final List<MultipleChoice> _result = new ArrayList<MultipleChoice>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MultipleChoice _item;
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpInstructions;
            if (_cursor.isNull(_cursorIndexOfInstructions)) {
              _tmpInstructions = null;
            } else {
              _tmpInstructions = _cursor.getString(_cursorIndexOfInstructions);
            }
            final int _tmpTotalMarks;
            _tmpTotalMarks = _cursor.getInt(_cursorIndexOfTotalMarks);
            _item = new MultipleChoice(_tmpLevel,null,null,null,null,null,_tmpInstructions,null,_tmpTotalMarks);
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
