// capturamos elementos del html en este caso del formualrio
const formualrio = document.getElementById("formLogin");
// 
const inputUsuario = document.getElementById("Usuario");
const inputPassword = document.getElementById("password")

//
formualrio.addEventListener("submit",function(e){
    //evitamos q se recarge la pagina
    e.preventDefault();

    const usuario = 
        inputUsuario.value;
    const password = 
        inputPassword.value;

    //ver datos en consola
    console.log(usuario);
    console.log(password);

    //condicional y validacion
    if(usuario=== "user" && password === "15963"){
        //ingresa a mi pagina principal por el momento
        window.location.href = "Principal.html";
        
    }else if(usuario==="admin" && password ==="14753"){
        //ingresa a mi pagina principal por el momento
        window.location.href = "dashboard.html";
   
    } else{
        alert("datos incorrectos")
    }
});

