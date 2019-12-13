package com.groupfour.chatapp.chatapp.poll;

import com.groupfour.chatapp.chatapp.poll.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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
