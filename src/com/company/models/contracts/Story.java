package com.company.models.contracts;

import com.company.models.enums.Size;

public interface Story extends TaskAssignment {

    Size getSize();

    void changeSize(Size size);
}
