package entry;

import enums.ChargeType;
import enums.TransactionType;

import java.util.Date;

/**
 * Created by 16544 on 27/01/18.
 */
public class ChargeTransactionEntry {

    private Long chargeId;
    private String eventReferenceId;
    private TransactionType transactionType;
    private ChargeType chargeType;
    private Date eventTime;
    private Double amount;
}

