package com.javaFullStackProject.e_commerce.services.admin.faq;

import com.javaFullStackProject.e_commerce.dto.FAQDto;
import com.javaFullStackProject.e_commerce.entity.FAQ;
import com.javaFullStackProject.e_commerce.entity.Product;
import com.javaFullStackProject.e_commerce.repository.FAQRepository;
import com.javaFullStackProject.e_commerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService{

    private final FAQRepository faqRepository;

    private final ProductRepository productRepository;


    public FAQDto postFAQ(Long productId, FAQDto faqDto){
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalProduct.isPresent()){
            FAQ faq = new FAQ();

            faq.setQuestion(faqDto.getQuestion());
            faq.setAnswer(faqDto.getAnswer());
            faq.setProduct(optionalProduct.get());

            return faqRepository.save(faq).getFAQDto();
        }

        return null;
    }
}
