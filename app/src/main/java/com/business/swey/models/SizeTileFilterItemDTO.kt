package com.business.swey.models

import com.business.swey.utils.Enum

class SizeTileFilterItemDTO {
    constructor(sizeType: Enum.SizeType, size: String, isSelected: Boolean) {
        this.sizeType = sizeType
        this.size = size
        this.isSelected = isSelected
    }

    var sizeType = com.business.swey.utils.Enum.SizeType.normal
    var size = ""
    var isSelected = false
}