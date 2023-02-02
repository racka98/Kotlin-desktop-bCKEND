class DaoLayer (
    sqlDriver: sqldriver,
        ){
    private val dbRef: DemoEntity = DemoEntity(sqlDriver)

    fun selectAll(): List<ReminderDb> =
        dbRef.tableQueries
            .selectAll()
            .executeAsList()

    fun insertReminder:(id: String, name: String,password:String,identification:String,data:String) {
        dbRef.tableQueries.insertReminder(id, name,password,identification,data)
    }

}
fun DemoEntity.map() = Reminder(
    id = this.id,
    name = this.name,
    password = this.password,
    identification =this.identification,
    data = this.data
)