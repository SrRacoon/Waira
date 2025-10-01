document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.querySelector("#login-modal form");
    const registerClienteForm = document.querySelector("#registerCliente-modal form");

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

    registerClienteForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const formData = new FormData(registerClienteForm);
        const payload = Object.fromEntries(formData.entries());

        const response = await fetch("/api/register/cliente", {
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
