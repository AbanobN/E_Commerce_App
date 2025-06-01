package com.javaFullStackProject.e_commerce.services.admin.coupon;

import com.javaFullStackProject.e_commerce.entity.Coupon;

import java.util.List;

public interface AdminCouponService {
    Coupon createCoupon(Coupon coupon);
    List<Coupon> getAllCoupons();
}
