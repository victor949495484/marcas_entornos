
document.addEventListener('DOMContentLoaded', () => {

   
    const infoPersonajes = {
        'indy': {
            titulo: "Arqueólogo Aventurero",
            detalles: ["Arma: Látigo", "Fuerza: 8/10", "Inteligencia: 10/10", "Habilidad: Descifrar enigmas"]
        },
        'ninja': {
            titulo: "Asesino Silencioso",
            detalles: ["Arma: Katana", "Agilidad: 10/10", "Sigilo: 10/10", "Habilidad: Caminar por sombras"]
        },
        'nazi': { 
            titulo: "Villano Militar",
            detalles: ["Arma: Pistola Luger", "Puntería: 9/10", "Maldad: 10/10", "Habilidad: Buen tirador"]
        }
    };

  
    const estiloDesplegable = document.createElement('style');
    estiloDesplegable.innerHTML = `
        .info-desplegable {
            max-height: 0;
            overflow: hidden;
            transition: max-height 0.4s ease-out, padding 0.4s ease;
            background-color: rgba(0, 0, 0, 0.8); /* Fondo oscuro semitransparente */
            color: #fff;
            font-family: 'Pixelify Sans', sans-serif;
            font-size: 0.9rem;
            border-radius: 0 0 10px 10px;
            padding: 0 15px;
            text-align: left;
            width: 100%;
            box-sizing: border-box; 
        }
        
        .info-desplegable.activo {
            padding: 15px;
            max-height: 200px; /* Altura máxima suficiente para el contenido */
        }

        .info-desplegable ul {
            list-style-type: none;
            padding: 0;
            margin: 5px 0 0 0;
        }

        .info-desplegable h4 {
            margin: 0 0 5px 0;
            color: #f1c40f; /* Color dorado para el título */
            font-size: 1rem;
        }

        /* Hacemos que el cursor sea una mano para indicar que es clicable */
        .tarjeta-personaje {
            cursor: pointer;
            transition: transform 0.2s;
            display: flex; /* Aseguramos flex para que el contenido fluya verticalmente */
            flex-direction: column;
            align-items: center;
        }
    `;
    document.head.appendChild(estiloDesplegable);

    
    const tarjetas = document.querySelectorAll('.tarjeta-personaje');

    tarjetas.forEach(tarjeta => {
       
        let clavePersonaje = '';
        if (tarjeta.classList.contains('indy')) clavePersonaje = 'indy';
        else if (tarjeta.classList.contains('ninja')) clavePersonaje = 'ninja';
        else if (tarjeta.classList.contains('nazi')) clavePersonaje = 'nazi';

        if (clavePersonaje && infoPersonajes[clavePersonaje]) {
           
            const datos = infoPersonajes[clavePersonaje];

            
            const divInfo = document.createElement('div');
            divInfo.className = 'info-desplegable';


            let listaHTML = '';
            datos.detalles.forEach(item => {
                listaHTML += `<li>• ${item}</li>`;
            });

            divInfo.innerHTML = `
                <h4>${datos.titulo}</h4>
                <ul>${listaHTML}</ul>
            `;

            
            tarjeta.appendChild(divInfo);

            tarjeta.addEventListener('click', (e) => {
                
                document.querySelectorAll('.info-desplegable').forEach(d => {
                    if (d !== divInfo) d.classList.remove('activo');
                });

              
                divInfo.classList.toggle('activo');
            });
        }
    });
});