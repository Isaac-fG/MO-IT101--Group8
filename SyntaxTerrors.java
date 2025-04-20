/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package group8.syntaxterrors;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.YearMonth;

/**
 *
 * @author IG(Ayszk)
 */
public class SyntaxTerrors {

   public static void main(String[] args) {
        System.out.println("\n====== MOTORPH PAYROLL SYSTEM ======\n");
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Monthly Basic Salary: ");
        double basicSalary = scanner.nextDouble();
        
        WorkingDaysInfo workingInfo = calculateWorkingDays();
        double hourlyRate = calculateHourlyRate(basicSalary, workingInfo.workingDays);
        double totalWorkingHours = workingInfo.totalWorkingHours;
        
        System.out.println("\n====== RATE INFORMATION ======");
        System.out.printf("Hourly Rate: %.2f%n", hourlyRate);
        System.out.printf("Total Working Hours: %.2f%n", totalWorkingHours);
        
        double totalSalary = calculateTotalSalary(hourlyRate, totalWorkingHours);
        
        double sssContribution = calculateSSS(totalSalary);
        double philHealthContribution = calculatePhilHealth(basicSalary);
        double pagIbigContribution = calculatePagIbig(basicSalary);
        
        double totalDeductions = sssContribution + philHealthContribution + pagIbigContribution;
        double taxableIncome = totalSalary - totalDeductions;
        double withholdingTax = calculateWithholdingTax(taxableIncome);

        displayResults(basicSalary, hourlyRate, totalSalary, workingInfo, sssContribution, 
                      philHealthContribution, pagIbigContribution, totalDeductions, 
                      taxableIncome, withholdingTax);

        scanner.close();
    }

    static class WorkingDaysInfo {
        int workingDays;
        int workingHoursPerDay;
        double totalWorkingHours;

        WorkingDaysInfo(int days, int hoursPerDay) {
            this.workingDays = days;
            this.workingHoursPerDay = hoursPerDay;
            this.totalWorkingHours = days * hoursPerDay;
        }
    }

    private static double calculateHourlyRate(double basicSalary, int workingDays) {
        int workingHoursPerDay = 8; // Excluding 1-hour break
        double totalWorkingHours = workingDays * workingHoursPerDay;
        return basicSalary / totalWorkingHours;
    }

    private static WorkingDaysInfo calculateWorkingDays() {
        YearMonth yearMonth = YearMonth.now();
        LocalDate firstDay = yearMonth.atDay(1);
        LocalDate lastDay = yearMonth.atEndOfMonth();
        int workingDays = 0;
        int workingHoursPerDay = 8; // 9 hours - 1 hour break

        for (LocalDate date = firstDay; !date.isAfter(lastDay); date = date.plusDays(1)) {
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && 
                date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                workingDays++;
            }
        }

        System.out.println("\n====== WORKING SCHEDULE ======");
        System.out.println("Working Hours: 8:00 AM - 5:00 PM");
        System.out.println("Break Time: 1 hour");
        System.out.println("Actual Working Hours per day: " + workingHoursPerDay);
        System.out.println("Total Working Days: " + workingDays);
        
        return new WorkingDaysInfo(workingDays, workingHoursPerDay);
    }

    private static double calculateTotalSalary(double hourlyRate, double totalWorkingHours) {
        return hourlyRate * totalWorkingHours;
    }

    private static double calculateSSS(double salary) {
        if (salary < 3250) return 135.00;
        if (salary <= 3750) return 157.50;
        if (salary <= 4250) return 180.00;
        if (salary <= 4750) return 202.50;
        if (salary <= 5250) return 225.00;
        if (salary <= 5750) return 247.50;
        if (salary <= 6250) return 270.00;
        if (salary <= 6750) return 292.50;
        if (salary <= 7250) return 315.00;
        if (salary <= 7750) return 337.50;
        if (salary <= 8250) return 360.00;
        if (salary <= 8750) return 382.50;
        if (salary <= 9250) return 405.00;
        if (salary <= 9750) return 427.50;
        if (salary <= 10250) return 450.00;
        if (salary <= 10750) return 472.50;
        if (salary <= 11250) return 495.00;
        if (salary <= 11750) return 517.50;
        if (salary <= 12250) return 540.00;
        if (salary <= 12750) return 562.50;
        if (salary <= 13250) return 585.00;
        if (salary <= 13750) return 607.50;
        if (salary <= 14250) return 630.00;
        if (salary <= 14750) return 652.50;
        if (salary <= 15250) return 675.00;
        if (salary <= 15750) return 697.50;
        if (salary <= 16250) return 720.00;
        if (salary <= 16750) return 742.50;
        if (salary <= 17250) return 765.00;
        if (salary <= 17750) return 787.50;
        if (salary <= 18250) return 810.00;
        if (salary <= 18750) return 832.50;
        if (salary <= 19250) return 855.00;
        if (salary <= 19750) return 877.50;
        if (salary <= 20250) return 900.00;
        if (salary <= 20750) return 922.50;
        if (salary <= 21250) return 945.00;
        if (salary <= 21750) return 967.50;
        if (salary <= 22250) return 990.00;
        if (salary <= 22750) return 1012.50;
        if (salary <= 23250) return 1035.00;
        if (salary <= 23750) return 1057.50;
        if (salary <= 24250) return 1080.00;
        if (salary <= 24750) return 1102.50;
        if (salary >= 24750) return 1125.00;
        return 1125.00; // Maximum contribution
    }

   private static double calculatePhilHealth(double basicSalary) {
        double rate = 0.03;
        double premium;
        
        if (basicSalary <= 10000) {
            premium = basicSalary * rate;
        } else if (basicSalary <= 59999.99) {
            premium = Math.min(basicSalary * rate, 1800);
        } else {
            premium = 1800;
        }
        
        return premium / 2;
    }

    private static double calculatePagIbig(double basicSalary) {
        double contributionRate;
        if (basicSalary >= 1000 && basicSalary <= 1500) {
            contributionRate = 0.01;
        } else {
            contributionRate = 0.02;
        }
        
        double contribution = basicSalary * contributionRate;
        return Math.min(contribution, 100);
    }

    private static double calculateWithholdingTax(double taxableIncome) {
        if (taxableIncome <= 20833) {
            return 0;
        }
        return (taxableIncome - 20833) * 0.20;
    }

    private static void displayResults(double basicSalary, double hourlyRate, 
            double totalSalary, WorkingDaysInfo workingInfo, double sssContribution, 
            double philHealthContribution, double pagIbigContribution, 
            double totalDeductions, double taxableIncome, double withholdingTax) {
        
        System.out.println("\n====== SALARY COMPUTATION ======");
        System.out.printf("Monthly Basic Salary: %.2f%n", basicSalary);
        System.out.printf("Hourly Rate: %.2f%n", hourlyRate);
        System.out.printf("Total Working Days: %d days%n", workingInfo.workingDays);
        System.out.printf("Total Working Hours: %.2f hours%n", workingInfo.totalWorkingHours);
        System.out.printf("Computed Salary: %.2f%n", totalSalary);

        System.out.println("\n====== DEDUCTIONS ======");
        System.out.printf("SSS Contribution: %.2f%n", sssContribution);
        
        System.out.println("\nPhilHealth Contribution:");
        System.out.printf("Monthly Basic Salary: %.2f%n", basicSalary);
        System.out.printf("3%% Premium Rate: %.2f%n", philHealthContribution * 2);
        System.out.printf("Employee Share (50%%): %.2f%n", philHealthContribution);
        
        System.out.printf("\nPag-IBIG Contribution: %.2f%n", pagIbigContribution);
        System.out.printf("Total Deductions: %.2f%n", totalDeductions);

        System.out.println("\n====== FINAL COMPUTATION ======");
        System.out.printf("Taxable Income: %.2f%n", taxableIncome);
        System.out.printf("Withholding Tax: %.2f%n", withholdingTax);
        System.out.printf("Net Pay: %.2f%n", taxableIncome - withholdingTax);
    }
}