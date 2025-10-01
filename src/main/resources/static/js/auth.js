document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.querySelector("#login-modal form");
    const registerClienteForm = document.querySelector("#registerCliente-modal form");
    const registerAdminForm = document.querySelector("#registerAdminForm");

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

    

    registerAdminForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const formData = new FormData(registerAdminForm);
        const permisos = formData.getAll("permisos");

        const payload = {
            name: formData.get("name"),
            surname: formData.get("surname"),
            phone: formData.get("phone"),
            email: formData.get("email"),
            password: formData.get("password"),
            confirmPassword: formData.get("confirmPassword"),
            permisos: permisos
        };

        const response = await fetch("/api/register/admin", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        const registerError = document.getElementById("register-admin-error");

        if (response.ok) {
            const data = await response.json();
            window.location.href = data.redirect;
        } else {
            const data = await response.json();
            registerError.textContent = data.error;
        }
    });
});
