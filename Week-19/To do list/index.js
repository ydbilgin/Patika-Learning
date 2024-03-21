// JavaScript dosyası: js/app.js

// Local Storage'dan verileri al
function getTasks() {
  return JSON.parse(localStorage.getItem("tasks")) || [];
}

// Local Storage'a verileri kaydet
function saveTasks(tasks) {
  localStorage.setItem("tasks", JSON.stringify(tasks));
}

// Yapılacaklar listesini göster
function displayTasks() {
  const tasks = getTasks();

  const list = document.getElementById("list");
  list.innerHTML = "";

  tasks.forEach((task, index) => {
    const listItem = document.createElement("li");
    listItem.textContent = task;

    // Yapıldı butonu
    const doneButton = document.createElement("button");
    doneButton.textContent = "Yapıldı";
    doneButton.classList.add("close");
    doneButton.addEventListener("click", function () {
      tasks.splice(index, 1);
      saveTasks(tasks);
      displayTasks();
    });

    listItem.appendChild(doneButton);
    list.appendChild(listItem);
  });
}

// Yeni bir görev ekle
function addTask() {
  const taskInput = document.getElementById("task");
  const task = taskInput.value.trim();

  if (task === "") {
    // Boş ekleme hatası
    const emptyToast = new bootstrap.Toast(
      document.getElementById("emptyToast")
    );
    emptyToast.show();
    return;
  }

  const tasks = getTasks();
  tasks.push(task);
  saveTasks(tasks);
  displayTasks();

  // Ekleme bildirimi
  const liveToast = new bootstrap.Toast(document.getElementById("liveToast"));
  liveToast.show();

  taskInput.value = "";
}

// Sayfa yüklendiğinde yapılacaklar listesini göster
document.addEventListener("DOMContentLoaded", displayTasks);
