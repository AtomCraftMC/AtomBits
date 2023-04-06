package ir.alijk.atombits.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ir.alijk.atombits.AtomBits;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;

@DatabaseTable(tableName = "bitssystem_players")
public class AtomPlayer {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField(columnName = "username", unique = true) @Getter
    private String userName;

    @DatabaseField(unique = true) @Getter
    private String uuid;

    @DatabaseField @Getter @Setter
    private long bits = 0;

    public AtomPlayer() {

    }

    public AtomPlayer(String userName, String uuid) {
        this(userName, uuid, 0);
    }

    public AtomPlayer(String userName, String uuid, long bits) {
        this.userName = userName;
        this.uuid = uuid;
        this.bits = bits;
    }

    public boolean save() {
        try {
            AtomBits.getAtomPlayersDao().update(this);
            return true;
        } catch (SQLException exception) {
            return false;
        }
    }

    public boolean create() {
        try {
            AtomBits.getAtomPlayersDao().createIfNotExists(this);
            return true;
        } catch (SQLException ignored) {
            return false;
        }
    }


    public static AtomPlayer findPlayer(String userName) {
        try {
            return AtomBits.getAtomPlayersDao().queryForEq("username", userName).get(0);
        } catch (SQLException|IndexOutOfBoundsException exception) {
            return null;
        }
    }
}
