"use client";

import Navbar from "../Components/Navbar";

export default function Perfil() {
  const usuario = {
    nombre: "Nombre de usuario",
    email: "usuario@example.com",
    telefono: "+502 1234 5678",
    registro: "********",
  };

  return (
    <main className="min-h-screen bg-white">
      <Navbar />

      <div className="flex justify-center mt-10 px-4">
        <div className="bg-white rounded-2xl shadow-lg p-8 w-full max-w-2xl">
          
          {/* Encabezado */}
          <div className="flex items-center space-x-6 mb-8">
            <img
              src="/avatar.png" // pon tu imagen de usuario
              alt="Avatar"
              className="w-24 h-24 rounded-full border-2 border-pink-500"
            />
            <div>
              <h1 className="text-2xl font-bold text-gray-800">{usuario.nombre}</h1>
              <p className="text-gray-500"></p>
            </div>
          </div>

          {/* Información */}
          <div className="space-y-4">
            <div className="flex justify-between p-4 bg-gray-50 rounded-lg shadow-sm">
              <span className="font-semibold text-gray-700">Correo electrónico:</span>
              <span className="text-gray-600">{usuario.email}</span>
            </div>


            <div className="flex justify-between p-4 bg-gray-50 rounded-lg shadow-sm">
              <span className="font-semibold text-gray-700">Contraseña:</span>
              <span className="text-gray-600">{usuario.registro}</span>
            </div>
          </div>

          {/* Botones de acción */}
          <div className="mt-6 flex space-x-4">
            <button className="bg-pink-500 text-white px-6 py-2 rounded-lg hover:bg-pink-600 transition-colors">
              Editar perfil
            </button>
            <button className="bg-gray-200 text-gray-700 px-6 py-2 rounded-lg hover:bg-gray-300 transition-colors">
              Cambiar contraseña
            </button>
            <button className="bg-gray-200 text-gray-700 px-6 py-2 rounded-lg hover:bg-gray-300 transition-colors">
              Cambiar correo electrónico
            </button>
          </div>
          {/* Cerrar sesión separado */}
        <div className="mt-10 border-t pt-6 flex justify-center">
          <button className="text-red-500 font-semibold hover:text-red-600 transition-colors">
            Cerrar sesión
          </button>
        </div>
        </div>
      </div>

    </main>
  );
}