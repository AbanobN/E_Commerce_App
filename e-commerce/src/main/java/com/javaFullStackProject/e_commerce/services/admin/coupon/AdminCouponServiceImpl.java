package com.javaFullStackProject.e_commerce.services.admin.coupon;

import com.javaFullStackProject.e_commerce.entity.Coupon;
import com.javaFullStackProject.e_commerce.exceptions.ValidationException;
import com.javaFullStackProject.e_commerce.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService{

    private final CouponRepository couponRepository;

    @Override
    public Coupon createCoupon(Coupon coupon){
        if(couponRepository.existsByCode(coupon.getCode())){
            throw new ValidationException("Coupon code already exists. ");
        }

        return couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> getAllCoupons(){
        return couponRepository.findAll();
    }
}
