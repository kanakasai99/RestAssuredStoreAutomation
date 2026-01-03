package pojo;

import io.reactivex.rxjava3.schedulers.SchedulerRunnableIntrospection;

/*/*
 {
    id:20,
    email:String,
    username:String,
    password:String,
    name:{
        firstname:String,
        lastname:String
        },
    address:{
    city:String,
    street:String,
    number:Number,
    zipcode:String,
    geolocation:{
        lat:String,
        long:String
        }
    },
    phone:String
}*/
public class User {
    private String email;
    private String username;
    private String password;
    private Name name;
    private Address address;
    private String phone;

    public User(String email, String username, String password,Name name, Address address,String phone){
        this.email=email;
        this.username=username;
        this.password=password;
        this.name=name;
        this.address=address;
        this.phone=phone;
    }
   public User(){

   }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public Name getName(){
        return name;
    }
    public void setName(Name name){
        this.name=name;
    }
    public Address getAddress(){
        return address;
    }
    public void setAddress(Address address){
        this.address=address;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
}
