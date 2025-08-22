// ProofResponse.java
package org.lck.gx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProofResponse {
    private String address;
    private String amount;   // 字符串 wei
    private String root;     // 0x...
    private List<String> proof;
    private Integer index;
}
