package ir.alijk.atombits.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import ir.alijk.atombits.AtomBits;
import ir.alijk.megacore.LocalCurrentTimeStampType;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.SQLException;

@DatabaseTable(tableName = "bitssystem_transactions")
public class Transaction {
    @DatabaseField(columnName = "id", generatedId = true) @Getter
    private int id;

    @DatabaseField @Getter @Setter
    private String sender;

    @DatabaseField @Getter @Setter
    private String receiver;

    @DatabaseField(canBeNull = true) @Getter @Setter
    private String reason;

    @DatabaseField @Getter @Setter
    private String action;

    @DatabaseField @Getter @Setter
    private long amount;

    @DatabaseField(columnName = "transaction_at", persisterClass = LocalCurrentTimeStampType.class,
            defaultValue = "CURRENT_TIMESTAMP()", readOnly = true) @Getter
    private Date transactionAt;

    public Transaction() {

    }

    public Transaction(String sender, String receiver, String reason, String action, long amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.reason = reason;
        this.action = action;
        this.amount = amount;
    }

    public boolean create() {
        try {
            AtomBits.getTransactionDao().createIfNotExists(this);
            return true;
        } catch (SQLException ignored) {
            return false;
        }
    }

}
