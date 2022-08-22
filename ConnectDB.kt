import java.sql.DriverManager
data class Ticket(val passenger_id:Int,val passenger_name:String,val passenger_age:Int,val gender:String,val phone:String)

fun main(args: Array<String>) {
    val jdbcUrl = "jdbc:mysql://localhost:3306/Trains"
    val connection = DriverManager.getConnection(jdbcUrl,"root","Soumensql@123")
    println(connection.isValid(0))

    //Insert query
    val res_insert = connection.createStatement().executeUpdate("Insert into Ticket(passenger_name,passenger_age,gender,phone)values('Ramesh',50,'M','9345678899')")
    if(res_insert>0){
        println("Record inserted successfully")
    }else{
        println("Unable to insert record")
    }

    //Update query

    val res_update = connection.createStatement().executeUpdate("Update Ticket set passenger_name = 'paramesh' where passenger_id = 2")
    if(res_insert>0){
        println("Record updated successfully")
    }else{
        println("Unable to update record")
    }

    //delete query

    val res_delete =  connection.createStatement().executeUpdate("delete from Ticket where passenger_id=4")
    if(res_delete>0){
        println("Record deleted successfully")
    }else{
        println("Unable to delete record")
    }

    //Select query

    val query = connection.prepareStatement("Select * from Ticket")
    val result = query.executeQuery()
    val passengers = mutableListOf<Ticket>()

    while(result.next()){
        val passenger_id = result.getInt("passenger_id")
        val passenger_name = result.getString("passenger_name")
        val passenger_age = result.getInt("passenger_age")
        val gender = result.getString("gender")
        val phone = result.getString("phone")

        passengers.add(Ticket(passenger_id,passenger_name,passenger_age,gender,phone))
    }
    println(passengers)
}
