document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.querySelector("#login-modal form");
    const registerForm = document.querySelector("#register-modal form");

    loginForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const formData = new FormData(loginForm);
        const payload = Object.fromEntries(formData.entries());

        const response = await fetch("/api/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        const loginError = document.getElementById("login-error");

        if (response.ok) {
            const data = await response.json();
            window.location.href = data.redirect;
        } else {
            const data = await response.json();
            loginError.textContent = data.error;
        }
    });

    registerForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const formData = new FormData(registerForm);
        const payload = Object.fromEntries(formData.entries());

        const response = await fetch("/api/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        const registerError = document.getElementById("register-error");

        if (response.ok) {
            const data = await response.json();
            window.location.href = data.redirect;
        } else {
            const data = await response.json();
            registerError.textContent = data.error;
        }
    });
});
