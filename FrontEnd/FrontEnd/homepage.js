// LOGIN PAGE

const loginBtn = document.getElementById("loginBtn");

loginBtn.addEventListener("click", function () {
    const userid = document.getElementById("userid").value;
    const password = document.getElementById("password").value;

    // Demo authentication (replace with backend later)
    if (userid === "admin" && password === "admin123") {
        navigateToAddBike();
    } else if (userid === "user" && password === "user123") {
        navigateToViewBike();
    } else {
        alert("❌ Invalid credentials!");
    }
});

// Function to navigate to Add Bike page
function navigateToAddBike() {
    window.location.href = "addbike.html";
}

// Function to navigate to View Bike page (for user)
function navigateToViewBike() {
    window.location.href = "viewbikesuser.html";
}

