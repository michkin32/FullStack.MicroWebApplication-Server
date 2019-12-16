package com.groupfour.chatapp.chatapp.services;

import com.groupfour.chatapp.chatapp.models.Option;
import com.groupfour.chatapp.chatapp.repositories.PollRepository;
import com.groupfour.chatapp.chatapp.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionService {

    private OptionRepository optionRepository;
    private PollRepository pollRepository;

    @Autowired
    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public Option getOptionById(Long optionId)  {
        return optionRepository.findById(optionId).get();
    }

    public Option createNewOption(Option newOption) {
        return optionRepository.save(newOption);
    }




}
