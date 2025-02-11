package com.feather.bz.common.domain.dto;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.domain
 * @className: EnumDTO
 * @author: feather
 * @description:
 * @since: 2025-02-11 11:07
 * @version: 1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnumDT0 implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;
}

