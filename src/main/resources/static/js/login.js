document.querySelector("form").addEventListener("submit", async function (e) {
  e.preventDefault();

  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  const credentials = {
    username,
    password
  };

  try {
    const response = await fetch("http://localhost:8080/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(credentials)
    });

    if (response.ok) {
      alert("Login realizado com sucesso!");
      // Redirecionar para página principal, dashboard, etc
      window.location.href = "/index";
    } else {
      alert("Usuário ou senha inválidos.");
    }
  } catch (error) {
    console.error("Erro ao fazer login:", error);
    alert("Erro ao conectar com o servidor.");
  }
});
