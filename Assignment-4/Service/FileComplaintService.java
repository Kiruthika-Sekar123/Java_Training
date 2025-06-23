package com.TelecomSystem.Service;

import java.util.ArrayList;
import java.util.List;

public class FileComplaintService {
    private List<String> complaints = new ArrayList<>();

    public void addComplaint(String complaint) {
        complaints.add(complaint);
        System.out.println("Complaint recorded: " + complaint);
    }

    public List<String> getComplaints() {
        return complaints;
    }
}
