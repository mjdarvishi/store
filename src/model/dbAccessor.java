package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class dbAccessor {
    
    private Connection conection;
    private static  dbAccessor accessor;
    public static  dbAccessor GetAccessor(){
        if(accessor==null)
            try {
                accessor=new dbAccessor();
        } catch (SQLException ex) {
            //Logger.getLogger(dbAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
           return accessor; 
    }

    public Connection getConection() {
        return conection;
    }
    private dbAccessor() throws SQLException {
        String Url = "jdbc:sqlserver://localhost;databaseName=storeDB";
        conection = DriverManager.getConnection(Url, "sa", "123456");
    }
    
    public String isValid(String username, String password) {
        try {
            Statement statement = conection.createStatement();
            ResultSet result = statement.executeQuery("SELECT [name]FROM [User] where userName='" + username + "'and password='" + password + "'");
            if (result.next()) {
                return result.getString("name");
            } else {
                return "bb";
            }
            
        } catch (SQLException ex) {
            
            return "bb";
        }
        
    }
    public ArrayList<Vector> GetItem(){
        ArrayList item=new ArrayList();
        String sql="SELECT code,[name],unitprice FROM Item";
        try {
            Statement s=conection.createStatement();
           ResultSet result= s.executeQuery(sql);
           while(result.next()){
               
         Vector<Object> t=new Vector();
            t.add(result.getInt(1));
            t.add(result.getString(2));
            t.add(result.getInt(3));
              item.add(t);
                      }
        } catch (SQLException ex) {
            Logger.getLogger(dbAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item ;
    }
    
    public boolean Add(String name, int price) {
        boolean db = false;
        String sql = "INSERT Item( [name],unitPrice)VALUES(?,?)";
        
        try {
            PreparedStatement ps = conection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, price);
            int i = ps.executeUpdate();
            if (i != 0) {
                db = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(dbAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
    public boolean addCustomer(String first,String sex,String last,String address,String tell){
        boolean db=false;
        String sql="INSERT INTO storeDB.dbo.Customer(fName,lName,[address],telNo,sex)VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps=conection.prepareStatement(sql);
            ps.setString(1,first);
            ps.setString(2,last);
            ps.setString(3,address);
            ps.setString(4,tell);
            ps.setString(5,sex);
            int i=ps.executeUpdate();
            if(i!=0)
                db=true;
            
        } catch (SQLException ex) {
            Logger.getLogger(dbAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return db;
        
    }
    public int GetId(String user){
        int i=0;
        String sql="SELECT userId FROM [User] WHERE userName=?";
        
        try {
            PreparedStatement s=conection.prepareStatement(sql);
            s.setString(1, user);
            ResultSet result=s.executeQuery();
            if(result.next())
             i=result.getInt(1);
                
        } catch (SQLException ex) {
            Logger.getLogger(dbAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
        
    }
    
    public List<Customer> getCustomer( String fname,String lname,String address,String tell,String sex){
            
        List<Customer>  customers=new ArrayList();
                EntityManagerFactory emf=Persistence.createEntityManagerFactory("storePU");
        EntityManager manager=emf.createEntityManager();
        String request="SELECT x from Customer x ";
        
        if(!fname.equals("")||!lname.equals("")||!address.equals("")||!tell.equals("")||!sex.equals("")){
                request+="where";
                if(!fname.equals("")){
                    request+=" x.fName like :q";                    
                }if(!lname.equals("")){
                    if(fname.equals(""))
                    request+=" x.lName like :w";
                    else
                        request+=" and x.lName like :w";
                }if(!address.equals("")){
                    if(fname.equals("")&&lname.equals(""))
                    request+=" x.address like :e";
                    else
                        request+=" and x.address like :e";
                }
                if(!tell.equals("")){
                    if(fname.equals("")&&lname.equals("")&&address.equals(""))
                    request+=" x.telNo like :r";
                    else
                        request+=" and x.telNo like :r";}
                     if(!sex.equals("")){
                    if(fname.equals("")&&lname.equals("")&&address.equals("")&&tell.equals(""))
                    request+=" x.sex like :t";
                    else
                        request+=" and x.sex like :t";
                }
                    
            }
        
          Query quary= manager.createQuery(request);
          
          if(!fname.equals(""))
          quary.setParameter("q",fname+"%");
          if(!lname.equals(""))
          quary.setParameter("w",lname+"%");
          if(!address.equals(""))
          quary.setParameter("e",address+"%");
          if(!tell.equals(""))
          quary.setParameter("r",tell+"%");
          if(!sex.equals(""))
          quary.setParameter("t",sex+"%");
          customers=quary.getResultList();
        return customers;
        
    }
    
    
    
    
    public static void main(String[] args) {
//        try {
         dbAccessor a;
        try {
            a = new dbAccessor();
            List<Customer> b= a.getCustomer("", "", "", "", "مذکر");
            System.out.println(b.size());
            for (Customer object : b) {
                System.out.print(object.getId());
                System.out.print(object.getFName());
                System.out.print(object.getLName());
                System.out.print(object.getAddress());
                System.out.print(object.getTelNo());
                System.out.print(object.getSex());
                System.out.println("");
            }
            dbAccessor q=GetAccessor();
            System.out.println(q.isValid("a", "12"));
//          ArrayList v=  a.GetItem();
//            for (Vector s : v) {
//                System.out.println(s.);
//            }
//             // a.addCustomer("first", "last","tehran","265845","mela");
//             System.out.println(  a.GetId("akbari"));
        } catch (SQLException ex) {
            Logger.getLogger(dbAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
//         boolean b=   a.Add("glass", 55000);
          //  System.out.println(b);

//        } catch (SQLException ex) {
//            Logger.getLogger(dbAccessor.class.getName()).log(Level.SEVERE, null, ex);
//        }
       
        
    }
    
}
