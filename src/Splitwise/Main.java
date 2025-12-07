import builder.ExpenseBuilder;
import java.util.List;
import models.Group;
import models.Person;
import strategy.SplitEqualPayStrategy;
import strategy.SplitFixedPayStrategy;
import strategy.SplitPercentageStrategy;

public class Main {
        public static void main(String[] args) {
                Splitwise swService = Splitwise.getSplitwiseInstance();

                Person p1 = swService.addPerson("Rishav");
                Person p2 = swService.addPerson("Meghna");
                Person p3 = swService.addPerson("Rahul");
                Person p4 = swService.addPerson("Sneha");

                Group g1 = swService.addGroup("Thailand Trip");

                swService.addPersonToGroup(g1, p1);
                swService.addPersonToGroup(g1, p2);
                swService.addPersonToGroup(g1, p3);
                swService.addPersonToGroup(g1, p4);

                swService.createExpense(new ExpenseBuilder().setDescription("Expense 1").setPaidBy(p1)
                                .setPeopleInvolved(List.of(p1, p2)).setTotalAmount(8000)
                                .setSplitStrategy(new SplitEqualPayStrategy()));

                swService.createExpense(new ExpenseBuilder().setDescription("Expense 2").setPaidBy(p2)
                                .setPeopleInvolved(List.of(p1, p2)).setTotalAmount(10000)
                                .setSplitStrategy(new SplitPercentageStrategy()).setSplitValues(List.of(80.0, 20.0)));

                swService.createExpense(new ExpenseBuilder().setDescription("Expense 3").setPaidBy(p1)
                                .setPeopleInvolved(List.of(p1, p2)).setTotalAmount(5000)
                                .setSplitStrategy(new SplitPercentageStrategy()).setSplitValues(List.of(40.0, 60.0)));

                swService.addGroupExpense(g1, new ExpenseBuilder().setDescription("Expense 4").setPaidBy(p3)
                                .setPeopleInvolved(List.of(p1, p2, p3, p4)).setTotalAmount(100000)
                                .setSplitStrategy(new SplitPercentageStrategy())
                                .setSplitValues(List.of(40.0, 40.0, 0.0, 20.0)));

                swService.addGroupExpense(g1, new ExpenseBuilder().setDescription("Expense 5").setPaidBy(p2)
                                .setPeopleInvolved(List.of(p1, p2, p3, p4)).setTotalAmount(100000)
                                .setSplitStrategy(new SplitFixedPayStrategy())
                                .setSplitValues(List.of(5000.0, 5000.0, 45000.0, 45000.0)));

                swService.addGroupExpense(g1, new ExpenseBuilder().setDescription("Expense 6").setPaidBy(p4)
                                .setPeopleInvolved(List.of(p1, p3, p4)).setTotalAmount(100000)
                                .setSplitStrategy(new SplitEqualPayStrategy()));

                swService.addTransaction(p1, p2, 5000);
                swService.addTransaction(p1, p3, 10000);

                System.out.println("Before Simplification : ");
                System.out.println(p1.getName() + "\n" + p1.getBalanceSheet());
                System.out.println(p2.getName() + "\n" + p2.getBalanceSheet());
                System.out.println(p3.getName() + "\n" + p3.getBalanceSheet());
                System.out.println(p4.getName() + "\n" + p4.getBalanceSheet());

                swService.simplifyDebts(g1);

                System.out.println("---------------------\n\n---------------------\n");

                System.out.println("After Simplification : ");
                System.out.println(p1.getName() + "\n" + p1.getBalanceSheet());
                System.out.println(p2.getName() + "\n" + p2.getBalanceSheet());
                System.out.println(p3.getName() + "\n" + p3.getBalanceSheet());
                System.out.println(p4.getName() + "\n" + p4.getBalanceSheet());
        }
}
