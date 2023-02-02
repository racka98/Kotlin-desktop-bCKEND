class DaoLayer (
    sqlDriver: sqldriver,
        ){
    private val dbRef: DemoEntity = DemoEntity(sqlDriver)

    fun selectAll(): List<ReminderDb> =
        dbRef.tableQueries
            .selectAll()
            .executeAsList()

    fun getLastInsert:(id: String, name: String) {
        dbRef.tableQueries.insertReminder(id, title)
    }

}
fun DemoEntity.map() = Reminder(
    id = this.id,
    name = this.title,
    data = this.isCompleted(),
)