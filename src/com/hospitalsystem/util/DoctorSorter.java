package com.hospitalsystem.util;

import com.hospitalsystem.model.Doctor;
import java.util.List;

/**
 * Member 2 - DSA Module: Selection Sort for Doctors
 * Sorts doctor records in-place by consultation fee ascending.
 * Time Complexity: O(n^2), Space Complexity: O(1).
 */
public class DoctorSorter {

    public static void sortByFeeAscending(List<Doctor> doctorList) {
        if (doctorList == null || doctorList.size() <= 1) {
            return;
        }

        int totalDoctors = doctorList.size();

        // One by one move the boundary of the unsorted subarray
        for (int i = 0; i < totalDoctors - 1; i++) {
            int minIndex = i;

            // Find the minimum element in unsorted array
            for (int j = i + 1; j < totalDoctors; j++) {
                if (doctorList.get(j).getFee() < doctorList.get(minIndex).getFee()) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element of unsorted sublist
            if (minIndex != i) {
                Doctor tempDoctor = doctorList.get(i);
                doctorList.set(i, doctorList.get(minIndex));
                doctorList.set(minIndex, tempDoctor);
            }
        }
    }
}
