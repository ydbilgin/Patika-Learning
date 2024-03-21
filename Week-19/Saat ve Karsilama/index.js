// Kullanıcıdan ismi al
var name = prompt("Lütfen isminizi girin:");

// İsim alanına yaz
document.getElementById("myName").innerText = name;

// Zamanı güncelle
function updateClock() {
  var now = new Date();
  var day = now.toLocaleDateString("tr-TR", { weekday: "long" });
  var time = now.toLocaleTimeString("tr-TR");
  document.getElementById("myClock").innerText = day + " " + time;
}

// Her saniyede saatı güncelle
setInterval(updateClock, 1000);

// İlk güncellemeyi yap
updateClock();
