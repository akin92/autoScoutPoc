package com.logicbig.example.beans;

import com.logicbig.example.MsgService;
import com.logicbig.example.model.AvarageListingBySeller;
import com.logicbig.example.model.MakeDistribution;
import com.logicbig.example.model.MostFiveContact;
import com.logicbig.example.service.AutoScoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class ReportBean {

    @Autowired
    private MsgService msgService;

    @Autowired
    private AutoScoutService autoScoutService;

    public String getMsg() {
        return msgService.getMsg();
    }

    public List<AvarageListingBySeller> getAvarageListingBySellerList() {
        return autoScoutService.getAllAvarageListingSeller();
    }

    public List<MakeDistribution> getMakeDistList() {
        return autoScoutService.getAllMakeDistributions();
    }

    public List<MostFiveContact> getMostFiveContactList(String date) {
        return autoScoutService.getAllMostFiveContacts(date);
    }

    public Integer getAvarageThirtyPercent() {
        return autoScoutService.getAvarageThirtyPercent();
    }
}