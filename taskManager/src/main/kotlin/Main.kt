data class Task(val name: String, val description: String, val priority: Priority)

enum class Priority {
    HIGH, MEDIUM, LOW
}

class TaskManager {
    private val tasks = mutableListOf<Task>()

    fun addTask(task: Task) {
        tasks.add(task)
    }

    fun deleteTask(task: Task) {
        if (task in tasks) {
            tasks.remove(task)
            println("Task deleted: $task")
        } else {
            println("Task not found: $task")
        }
    }

    fun getTasks(): List<Task> {
        return tasks.toList()
    }
}

fun displayTasks(tasks: List<Task>) {
    tasks.sortedBy { it.priority }.forEach { task ->
        println("Task Name: ${task.name}")
        println("Description: ${task.description}")
        println("Priority: ${task.priority}")
        println()
    }
}

fun main() {
    val taskManager = TaskManager()

    while (true) {
        println("Task Manager Menu:")
        println("1. Add Task")
        println("2. Delete Task")
        println("3. List Tasks")
        println("4. Quit")
        print("Enter your choice: ")

        when (readLine()) {
            "1" -> {
                print("Enter task: ")
                val name = readLine()?.capitalize() ?: ""

                print("Enter task description: ")
                val description = readLine()?.capitalize() ?: ""

                print("Enter task priority (HIGH, MEDIUM, LOW): ")
                val priorityInput = readLine()?.toUpperCase() ?: ""
                val priority = try {
                    Priority.valueOf(priorityInput)
                } catch (e: IllegalArgumentException) {
                    Priority.LOW
                }

                val task = Task(name, description, priority)
                taskManager.addTask(task)
            }
            "2" -> {
                val tasks = taskManager.getTasks()
                if (tasks.isEmpty()) {
                    println("No tasks to delete.")
                } else {
                    println("Task List:")
                    displayTasks(tasks)

                    print("Enter task name to delete: ")
                    val name = readLine()?.toLowerCase() ?: ""
                    val taskToDelete = tasks.find { it.name.toLowerCase() == name }

                    if (taskToDelete != null) {
                        taskManager.deleteTask(taskToDelete)
                    } else {
                        println("Task not found: $name")
                    }
                }
            }
            "3" -> {
                val tasks = taskManager.getTasks()
                if (tasks.isEmpty()) {
                    println("No tasks to display.")
                } else {
                    println("Task List:")
                    displayTasks(tasks)
                }
            }
            "4" -> {
                println("Goodbye!")
                return
            }
            else -> println("Invalid choice. Please select a valid option.")
        }
    }
}
