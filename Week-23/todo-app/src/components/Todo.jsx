import { useState } from "react";

function TodoApp() {
  const [todos, setTodos] = useState([
    { text: "Taste JavaScript", done: true },
    { text: "Code furiously", done: true },
    { text: "Promote Mavo", done: false },
    { text: "Give talks", done: false },
    { text: "Write tutorials", done: true },
    { text: "Have a life!", done: false },
  ]);
  const [newTodo, setNewTodo] = useState("");
  const [activeFilter, setActiveFilter] = useState("all");

  const addTodo = (e) => {
    e.preventDefault();
    if (newTodo.trim() !== "") {
      setTodos([...todos, { text: newTodo.trim(), done: false }]);
      setNewTodo("");
    }
  };

  const deleteTodo = (index) => {
    setTodos(todos.filter((_, i) => i !== index));
  };

  const toggleTodo = (index) => {
    const updatedTodos = [...todos];
    updatedTodos[index].done = !updatedTodos[index].done;
    setTodos(updatedTodos);
  };

  const clearCompleted = () => {
    setTodos(todos.filter((todo) => !todo.done));
  };

  return (
    <section className="todoapp">
      <header className="header">
        <h1>todos</h1>
        <form onSubmit={addTodo}>
          <input
            className="new-todo"
            placeholder="What needs to be done?"
            value={newTodo}
            onChange={(e) => setNewTodo(e.target.value)}
            autoFocus
          />
        </form>
      </header>
      <section
        className="main"
        style={{ display: todos.length === 0 ? "none" : "block" }}
      >
        <input
          id="toggle-all"
          className="toggle-all"
          type="checkbox"
          checked={todos.filter((todo) => !todo.done).length === 0}
          onChange={() => {
            const allDone = todos.filter((todo) => !todo.done).length === 0;
            setTodos(todos.map((todo) => ({ ...todo, done: !allDone })));
          }}
        />
        <label htmlFor="toggle-all">Mark all as complete</label>
        <ul className="todo-list">
          {todos.map((todo, index) => (
            <li
              key={index}
              className={todo.done ? "completed" : ""}
              style={{
                display:
                  (todo.done && activeFilter === "active") ||
                  (!todo.done && activeFilter === "completed")
                    ? "none"
                    : "block",
              }}
            >
              <div className="view">
                <input
                  className="toggle"
                  type="checkbox"
                  checked={todo.done}
                  onChange={() => toggleTodo(index)}
                />
                <label>{todo.text}</label>
                <button
                  className="destroy"
                  onClick={() => deleteTodo(index)}
                ></button>
              </div>
            </li>
          ))}
        </ul>
      </section>
      <footer
        className="footer"
        style={{ display: todos.length === 0 ? "none" : "block" }}
      >
        <span className="todo-count">
          {todos.filter((todo) => !todo.done).length} item
          {todos.filter((todo) => !todo.done).length !== 1 ? "s" : ""} left
        </span>
        <ul className="filters">
          <li>
            <a
              className={activeFilter === "all" ? "selected" : ""}
              onClick={() => setActiveFilter("all")}
            >
              All
            </a>
          </li>
          <li>
            <a
              className={activeFilter === "active" ? "selected" : ""}
              onClick={() => setActiveFilter("active")}
            >
              Active
            </a>
          </li>
          <li>
            <a
              className={activeFilter === "completed" ? "selected" : ""}
              onClick={() => setActiveFilter("completed")}
            >
              Completed
            </a>
          </li>
        </ul>
        <button
          className="clear-completed"
          onClick={clearCompleted}
          style={{
            display: todos.some((todo) => todo.done) ? "block" : "none",
          }}
        >
          Clear completed
        </button>
      </footer>
    </section>
  );
}

export default TodoApp;
