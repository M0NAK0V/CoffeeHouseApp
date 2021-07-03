package com.coffeehouse.web.cartlist;

import com.coffeehouse.service.CartlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class CartlistController {
    private final CartlistService cartlistService;


}
