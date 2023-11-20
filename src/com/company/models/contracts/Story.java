package com.company.models.contracts;

import com.company.models.enums.Size;

public interface Story extends IntermediateTask {

    Size getSize();

    void changeSize(Size size);
}
