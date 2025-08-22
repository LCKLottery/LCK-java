// CreateBatchRequest.java
package org.lck.gx.dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.math.BigInteger;
import java.util.Map;

@Data
public class CreateBatchRequest {
  private String name;
  @NotEmpty
  private Map<String, BigInteger> addressAmountMap; // addr -> amount(wei)
}
