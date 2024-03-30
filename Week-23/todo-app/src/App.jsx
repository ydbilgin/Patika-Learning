import React, { useState } from "react";

const TodoApp = () => {
  const [todos, setTodos] = useState([]);
  const [newTodo, setNewTodo] = useState("");
  const [activeFilter, setActiveFilter] = useState("all");

  const addTodo = (text) => {
    if (text.trim() !== "") {
      setTodos([...todos, { text, done: false }]);
      setNewTodo("");
    }
  };

  const deleteTodo = (todoToDelete) => {
    setTodos(todos.filter((todo) => todo !== todoToDelete));
  };

  const toggleTodo = (todoToToggle) => {
    setTodos(
      todos.map((todo) =>
        todo === todoToToggle ? { ...todo, done: !todo.done } : todo
      )
    );
  };

  const clearCompleted = () => {
    setTodos(todos.filter((todo) => !todo.done));
  };

  return (
    <section className="todoapp">
      <header className="header">
        <h1>todos</h1>
        <form
          onSubmit={(e) => {
            e.preventDefault();
            addTodo(newTodo.trim());
          }}
        >
          <input
            value={newTodo}
            onChange={(e) => setNewTodo(e.target.value)}
            className="new-todo"
            placeholder="What needs to be done?"
            autoFocus
          />
        </form>
      </header>

      <section hidden={todos.length === 0} className="main">
        <input
          id="toggle-all"
          className="toggle-all"
          type="checkbox"
          checked={todos.every((todo) => todo.done)}
          onChange={() =>
            setTodos(todos.map((todo) => ({ ...todo, done: !todo.done })))
          }
        />
        <label htmlFor="toggle-all">Mark all as complete</label>

        <ul className="todo-list">
          {todos.map((todo) => (
            <li
              key={todo.text}
              className={todo.done ? "completed" : ""}
              hidden={
                (todo.done && activeFilter === "active") ||
                (!todo.done && activeFilter === "completed")
              }
            >
              <div className="view">
                <input
                  type="checkbox"
                  className="toggle"
                  checked={todo.done}
                  onChange={() => toggleTodo(todo)}
                />
                <label>{todo.text}</label>
                <button
                  className="destroy"
                  onClick={() => deleteTodo(todo)}
                ></button>
              </div>
            </li>
          ))}
        </ul>
      </section>

      <footer hidden={todos.length === 0} className="footer">
        <span className="todo-count">
          {todos.filter((todo) => !todo.done).length}{" "}
          {todos.filter((todo) => !todo.done).length === 1 ? "item" : "items"}{" "}
          left
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
          hidden={todos.filter((todo) => todo.done).length === 0}
          className="clear-completed"
          onClick={clearCompleted}
        >
          Clear completed
        </button>
      </footer>
    </section>
  );
};

export default TodoApp;
