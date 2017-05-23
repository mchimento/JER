package main;

import java.util.Collection;

public class CMachine {
   
   public int cups;
   public int limit;
   public boolean active;
   public Integer x;
   public Foo foo;

   CMachine(int limit) {
        this.limit = limit;
        cups = 0;
        active = false;
   }

   public void cleanF() {
       if (!active)
          cups = 0;
   }

   public void brew() {
       if (!active && cups < limit) 
          cups++;
   }
   
   public int getCups() {
      return cups;
   }

   public void setCups(int x) {
      cups = x;
      limit = limit + x + foo.goo.goo();
   }

   public boolean test(Collection c) {
          return c.isEmpty();
   }

}
