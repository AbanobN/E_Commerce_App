package com.javaFullStackProject.e_commerce.services.admin.faq;

import com.javaFullStackProject.e_commerce.dto.FAQDto;

public interface FAQService {

    FAQDto postFAQ(Long productId, FAQDto faqDto);
}
