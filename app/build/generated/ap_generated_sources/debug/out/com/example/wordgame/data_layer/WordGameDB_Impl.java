package com.example.wordgame.data_layer;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class WordGameDB_Impl extends WordGameDB {
  private volatile LearnDao _learnDao;

  private volatile MatchingDao _matchingDao;

  private volatile TrueFalseDao _trueFalseDao;

  private volatile MultipleChoiceDao _multipleChoiceDao;

  private volatile TranslationDao _translationDao;

  private volatile LevelResultsDao _levelResultsDao;

  private volatile ProgressReportDao _progressReportDao;

  private volatile UserDao _userDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(10) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Learn` (`learnId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `level` INTEGER NOT NULL, `section` TEXT, `content` TEXT, `instruction` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Matching` (`matchingId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `level` INTEGER NOT NULL, `tittle` TEXT, `instructions` TEXT, `questions` TEXT, `answers` TEXT, `totalMarks` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TrueOfFalse` (`trueFalseId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `level` INTEGER NOT NULL, `question` TEXT, `figures` TEXT, `answers` TEXT, `instructions` TEXT, `totalMarks` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `MultipleChoice` (`multipleId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `level` INTEGER NOT NULL, `question` TEXT, `choiceOne` TEXT, `choiceTwo` TEXT, `choiceThree` TEXT, `choiceFour` TEXT, `answer` TEXT, `instructions` TEXT, `totalMarks` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TranslationGame` (`translationId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `hints` TEXT, `hintCounter` INTEGER NOT NULL, `instructions` TEXT, `totalMarks` INTEGER NOT NULL, `level` INTEGER NOT NULL, `question` TEXT, `answers` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `LevelResults` (`level_resultsId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `gameId` INTEGER NOT NULL, `userId` INTEGER NOT NULL, `level` INTEGER NOT NULL, `gameType` TEXT, `userMarks` REAL NOT NULL, `totalMarks` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ProgressReport` (`progressId` INTEGER NOT NULL, `userId` INTEGER NOT NULL, `levelOneScore` INTEGER NOT NULL, `levelTwoScore` INTEGER NOT NULL, `levelThreeScore` INTEGER NOT NULL, `AverageScore` INTEGER NOT NULL, `status` TEXT, PRIMARY KEY(`progressId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `User` (`userId` INTEGER NOT NULL, `currentLevel` INTEGER NOT NULL, PRIMARY KEY(`userId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '8b0d60f473aeee0f2e6b973417d7538a')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Learn`");
        _db.execSQL("DROP TABLE IF EXISTS `Matching`");
        _db.execSQL("DROP TABLE IF EXISTS `TrueOfFalse`");
        _db.execSQL("DROP TABLE IF EXISTS `MultipleChoice`");
        _db.execSQL("DROP TABLE IF EXISTS `TranslationGame`");
        _db.execSQL("DROP TABLE IF EXISTS `LevelResults`");
        _db.execSQL("DROP TABLE IF EXISTS `ProgressReport`");
        _db.execSQL("DROP TABLE IF EXISTS `User`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsLearn = new HashMap<String, TableInfo.Column>(5);
        _columnsLearn.put("learnId", new TableInfo.Column("learnId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearn.put("level", new TableInfo.Column("level", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearn.put("section", new TableInfo.Column("section", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearn.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearn.put("instruction", new TableInfo.Column("instruction", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLearn = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLearn = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLearn = new TableInfo("Learn", _columnsLearn, _foreignKeysLearn, _indicesLearn);
        final TableInfo _existingLearn = TableInfo.read(_db, "Learn");
        if (! _infoLearn.equals(_existingLearn)) {
          return new RoomOpenHelper.ValidationResult(false, "Learn(com.example.wordgame.model_layer.Learn).\n"
                  + " Expected:\n" + _infoLearn + "\n"
                  + " Found:\n" + _existingLearn);
        }
        final HashMap<String, TableInfo.Column> _columnsMatching = new HashMap<String, TableInfo.Column>(7);
        _columnsMatching.put("matchingId", new TableInfo.Column("matchingId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatching.put("level", new TableInfo.Column("level", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatching.put("tittle", new TableInfo.Column("tittle", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatching.put("instructions", new TableInfo.Column("instructions", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatching.put("questions", new TableInfo.Column("questions", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatching.put("answers", new TableInfo.Column("answers", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMatching.put("totalMarks", new TableInfo.Column("totalMarks", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMatching = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMatching = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMatching = new TableInfo("Matching", _columnsMatching, _foreignKeysMatching, _indicesMatching);
        final TableInfo _existingMatching = TableInfo.read(_db, "Matching");
        if (! _infoMatching.equals(_existingMatching)) {
          return new RoomOpenHelper.ValidationResult(false, "Matching(com.example.wordgame.model_layer.Matching).\n"
                  + " Expected:\n" + _infoMatching + "\n"
                  + " Found:\n" + _existingMatching);
        }
        final HashMap<String, TableInfo.Column> _columnsTrueOfFalse = new HashMap<String, TableInfo.Column>(7);
        _columnsTrueOfFalse.put("trueFalseId", new TableInfo.Column("trueFalseId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrueOfFalse.put("level", new TableInfo.Column("level", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrueOfFalse.put("question", new TableInfo.Column("question", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrueOfFalse.put("figures", new TableInfo.Column("figures", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrueOfFalse.put("answers", new TableInfo.Column("answers", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrueOfFalse.put("instructions", new TableInfo.Column("instructions", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrueOfFalse.put("totalMarks", new TableInfo.Column("totalMarks", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTrueOfFalse = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTrueOfFalse = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTrueOfFalse = new TableInfo("TrueOfFalse", _columnsTrueOfFalse, _foreignKeysTrueOfFalse, _indicesTrueOfFalse);
        final TableInfo _existingTrueOfFalse = TableInfo.read(_db, "TrueOfFalse");
        if (! _infoTrueOfFalse.equals(_existingTrueOfFalse)) {
          return new RoomOpenHelper.ValidationResult(false, "TrueOfFalse(com.example.wordgame.model_layer.TrueFalseGame).\n"
                  + " Expected:\n" + _infoTrueOfFalse + "\n"
                  + " Found:\n" + _existingTrueOfFalse);
        }
        final HashMap<String, TableInfo.Column> _columnsMultipleChoice = new HashMap<String, TableInfo.Column>(10);
        _columnsMultipleChoice.put("multipleId", new TableInfo.Column("multipleId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultipleChoice.put("level", new TableInfo.Column("level", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultipleChoice.put("question", new TableInfo.Column("question", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultipleChoice.put("choiceOne", new TableInfo.Column("choiceOne", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultipleChoice.put("choiceTwo", new TableInfo.Column("choiceTwo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultipleChoice.put("choiceThree", new TableInfo.Column("choiceThree", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultipleChoice.put("choiceFour", new TableInfo.Column("choiceFour", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultipleChoice.put("answer", new TableInfo.Column("answer", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultipleChoice.put("instructions", new TableInfo.Column("instructions", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultipleChoice.put("totalMarks", new TableInfo.Column("totalMarks", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMultipleChoice = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMultipleChoice = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMultipleChoice = new TableInfo("MultipleChoice", _columnsMultipleChoice, _foreignKeysMultipleChoice, _indicesMultipleChoice);
        final TableInfo _existingMultipleChoice = TableInfo.read(_db, "MultipleChoice");
        if (! _infoMultipleChoice.equals(_existingMultipleChoice)) {
          return new RoomOpenHelper.ValidationResult(false, "MultipleChoice(com.example.wordgame.model_layer.MultipleChoice).\n"
                  + " Expected:\n" + _infoMultipleChoice + "\n"
                  + " Found:\n" + _existingMultipleChoice);
        }
        final HashMap<String, TableInfo.Column> _columnsTranslationGame = new HashMap<String, TableInfo.Column>(8);
        _columnsTranslationGame.put("translationId", new TableInfo.Column("translationId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranslationGame.put("hints", new TableInfo.Column("hints", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranslationGame.put("hintCounter", new TableInfo.Column("hintCounter", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranslationGame.put("instructions", new TableInfo.Column("instructions", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranslationGame.put("totalMarks", new TableInfo.Column("totalMarks", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranslationGame.put("level", new TableInfo.Column("level", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranslationGame.put("question", new TableInfo.Column("question", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranslationGame.put("answers", new TableInfo.Column("answers", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTranslationGame = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTranslationGame = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTranslationGame = new TableInfo("TranslationGame", _columnsTranslationGame, _foreignKeysTranslationGame, _indicesTranslationGame);
        final TableInfo _existingTranslationGame = TableInfo.read(_db, "TranslationGame");
        if (! _infoTranslationGame.equals(_existingTranslationGame)) {
          return new RoomOpenHelper.ValidationResult(false, "TranslationGame(com.example.wordgame.model_layer.TranslationGame).\n"
                  + " Expected:\n" + _infoTranslationGame + "\n"
                  + " Found:\n" + _existingTranslationGame);
        }
        final HashMap<String, TableInfo.Column> _columnsLevelResults = new HashMap<String, TableInfo.Column>(7);
        _columnsLevelResults.put("level_resultsId", new TableInfo.Column("level_resultsId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLevelResults.put("gameId", new TableInfo.Column("gameId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLevelResults.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLevelResults.put("level", new TableInfo.Column("level", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLevelResults.put("gameType", new TableInfo.Column("gameType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLevelResults.put("userMarks", new TableInfo.Column("userMarks", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLevelResults.put("totalMarks", new TableInfo.Column("totalMarks", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLevelResults = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLevelResults = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLevelResults = new TableInfo("LevelResults", _columnsLevelResults, _foreignKeysLevelResults, _indicesLevelResults);
        final TableInfo _existingLevelResults = TableInfo.read(_db, "LevelResults");
        if (! _infoLevelResults.equals(_existingLevelResults)) {
          return new RoomOpenHelper.ValidationResult(false, "LevelResults(com.example.wordgame.model_layer.LevelResults).\n"
                  + " Expected:\n" + _infoLevelResults + "\n"
                  + " Found:\n" + _existingLevelResults);
        }
        final HashMap<String, TableInfo.Column> _columnsProgressReport = new HashMap<String, TableInfo.Column>(7);
        _columnsProgressReport.put("progressId", new TableInfo.Column("progressId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgressReport.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgressReport.put("levelOneScore", new TableInfo.Column("levelOneScore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgressReport.put("levelTwoScore", new TableInfo.Column("levelTwoScore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgressReport.put("levelThreeScore", new TableInfo.Column("levelThreeScore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgressReport.put("AverageScore", new TableInfo.Column("AverageScore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgressReport.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProgressReport = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProgressReport = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProgressReport = new TableInfo("ProgressReport", _columnsProgressReport, _foreignKeysProgressReport, _indicesProgressReport);
        final TableInfo _existingProgressReport = TableInfo.read(_db, "ProgressReport");
        if (! _infoProgressReport.equals(_existingProgressReport)) {
          return new RoomOpenHelper.ValidationResult(false, "ProgressReport(com.example.wordgame.model_layer.ProgressReport).\n"
                  + " Expected:\n" + _infoProgressReport + "\n"
                  + " Found:\n" + _existingProgressReport);
        }
        final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(2);
        _columnsUser.put("userId", new TableInfo.Column("userId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("currentLevel", new TableInfo.Column("currentLevel", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUser = new TableInfo("User", _columnsUser, _foreignKeysUser, _indicesUser);
        final TableInfo _existingUser = TableInfo.read(_db, "User");
        if (! _infoUser.equals(_existingUser)) {
          return new RoomOpenHelper.ValidationResult(false, "User(com.example.wordgame.model_layer.User).\n"
                  + " Expected:\n" + _infoUser + "\n"
                  + " Found:\n" + _existingUser);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "8b0d60f473aeee0f2e6b973417d7538a", "9945f8d7bc9f7742bc6622d3764cb5a9");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Learn","Matching","TrueOfFalse","MultipleChoice","TranslationGame","LevelResults","ProgressReport","User");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Learn`");
      _db.execSQL("DELETE FROM `Matching`");
      _db.execSQL("DELETE FROM `TrueOfFalse`");
      _db.execSQL("DELETE FROM `MultipleChoice`");
      _db.execSQL("DELETE FROM `TranslationGame`");
      _db.execSQL("DELETE FROM `LevelResults`");
      _db.execSQL("DELETE FROM `ProgressReport`");
      _db.execSQL("DELETE FROM `User`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(LearnDao.class, LearnDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MatchingDao.class, MatchingDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TrueFalseDao.class, TrueFalseDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MultipleChoiceDao.class, MultipleChoiceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TranslationDao.class, TranslationDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LevelResultsDao.class, LevelResultsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProgressReportDao.class, ProgressReportDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public LearnDao learnDao() {
    if (_learnDao != null) {
      return _learnDao;
    } else {
      synchronized(this) {
        if(_learnDao == null) {
          _learnDao = new LearnDao_Impl(this);
        }
        return _learnDao;
      }
    }
  }

  @Override
  public MatchingDao matchingDao() {
    if (_matchingDao != null) {
      return _matchingDao;
    } else {
      synchronized(this) {
        if(_matchingDao == null) {
          _matchingDao = new MatchingDao_Impl(this);
        }
        return _matchingDao;
      }
    }
  }

  @Override
  public TrueFalseDao trueFalseDao() {
    if (_trueFalseDao != null) {
      return _trueFalseDao;
    } else {
      synchronized(this) {
        if(_trueFalseDao == null) {
          _trueFalseDao = new TrueFalseDao_Impl(this);
        }
        return _trueFalseDao;
      }
    }
  }

  @Override
  public MultipleChoiceDao multipleChoiceDao() {
    if (_multipleChoiceDao != null) {
      return _multipleChoiceDao;
    } else {
      synchronized(this) {
        if(_multipleChoiceDao == null) {
          _multipleChoiceDao = new MultipleChoiceDao_Impl(this);
        }
        return _multipleChoiceDao;
      }
    }
  }

  @Override
  public TranslationDao translationDao() {
    if (_translationDao != null) {
      return _translationDao;
    } else {
      synchronized(this) {
        if(_translationDao == null) {
          _translationDao = new TranslationDao_Impl(this);
        }
        return _translationDao;
      }
    }
  }

  @Override
  public LevelResultsDao levelResultsDao() {
    if (_levelResultsDao != null) {
      return _levelResultsDao;
    } else {
      synchronized(this) {
        if(_levelResultsDao == null) {
          _levelResultsDao = new LevelResultsDao_Impl(this);
        }
        return _levelResultsDao;
      }
    }
  }

  @Override
  public ProgressReportDao progressReportDao() {
    if (_progressReportDao != null) {
      return _progressReportDao;
    } else {
      synchronized(this) {
        if(_progressReportDao == null) {
          _progressReportDao = new ProgressReportDao_Impl(this);
        }
        return _progressReportDao;
      }
    }
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }
}
