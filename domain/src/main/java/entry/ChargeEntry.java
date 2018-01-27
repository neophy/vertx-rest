package entry;

import enums.AppliedEntityType;
import enums.ChargeType;
import enums.ClaimedEntityType;
import enums.OwnerType;

import java.util.List;

/**
 * Created by 16544 on 27/01/18.
 */
public class ChargeEntry {

    private ChargeType type;
    private String chargeCode;
    private Long orderId;
    private String groupId;
    private OwnerType ownerType;
    private String ownerId;
    private AppliedEntityType appliedEntityType;
    private String appliedEntityId;
    private ClaimedEntityType claimEntityType;
    private String claimEntityId;
    private Double paidAmount;
    private Double activeAmount;
    private List<ChargeTransactionEntry> chargeTransactions;
}