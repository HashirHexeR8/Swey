package com.business.swey.core.models

import com.business.swey.core.utils.Enum

class SizeTileFilterItemDTO {
    constructor(sizeType: Enum.SizeType, size: String, isSelected: Boolean) {
        this.sizeType = sizeType
        this.size = size
        this.isSelected = isSelected
    }

    var sizeType = Enum.SizeType.normal
    var size = ""
    var isSelected = false
}