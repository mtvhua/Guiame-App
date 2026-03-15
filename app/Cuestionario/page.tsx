"use client";

import { useState } from "react";
import Image from "next/image";
import Navbar from "../Components/Navbar";
import { useRouter } from "next/navigation";

export default function CuestionarioPaso() {

  const preguntas = [
    {
      titulo: "Inspección Visual",
      texto: "¿Observaste algún cambio visible en tus senos?",
      imagen: "/pasos.jpg"
    },
    {
      titulo: "Palpación",
      texto: "¿Sentiste algún bulto o endurecimiento en el seno?",
      imagen: "/pasos.jpg"
    },
    {
      titulo: "Pezones",
      texto: "¿Observaste secreción o cambios en el pezón?",
      imagen: "/pasos.jpg"
    }
  ];

  const [paso, setPaso] = useState(0);
  const router = useRouter();
  const [respuesta, setRespuesta] = useState<string | null>(null);

  const siguientePaso = () => {
  if (paso < preguntas.length - 1) {
    setPaso(paso + 1);
    setRespuesta(null);
  } else {
    router.push("/"); // redirige a la página principal
  }
};

  return (
    <main className="min-h-screen w-full bg-white">

      <Navbar />

      <div className="flex flex-col items-center justify-center px-8 mt-10">

        {/* Paso */}
        <div className="flex items-center gap-4 mb-8">
          <div className="w-14 h-14 rounded-full border-4 border-pink-500 flex items-center justify-center text-pink-600 font-bold text-xl">
            {paso + 1}
          </div>

          <h1 className="text-3xl font-bold text-pink-600">
            {preguntas[paso].titulo}
          </h1>
        </div>

        {/* Tarjeta */}
        <div className="w-full max-w-5xl bg-gray-50 rounded-2xl shadow-md p-10 grid md:grid-cols-2 gap-10">

          <div>
            <p className="text-gray-700">
              {preguntas[paso].texto}
            </p>
          </div>

          <div className="flex justify-center items-center">
            <Image
              src={preguntas[paso].imagen}
              alt="Paso del examen"
              width={250}
              height={250}
            />
          </div>

        </div>

        {/* Botones */}
        <div className="flex justify-center gap-6 mt-8">

          <button
            onClick={() => setRespuesta("No")}
            className={`px-10 py-3 rounded-full font-semibold ${
              respuesta === "No"
                ? "bg-pink-500 text-white"
                : "bg-pink-100 text-pink-600"
            }`}
          >
            No
          </button>

          <button
            onClick={() => setRespuesta("Sí")}
            className={`px-10 py-3 rounded-full font-semibold ${
              respuesta === "Sí"
                ? "bg-pink-500 text-white"
                : "bg-pink-200 text-pink-700"
            }`}
          >
            Sí
          </button>

        </div>

        {/* Continuar */}
        <button
            onClick={siguientePaso}
            className="mt-8 bg-pink-500 text-white px-12 py-3 rounded-full shadow-md hover:bg-pink-600"
          >
            {paso === preguntas.length - 1 ? "Finalizar" : "Continuar"}
          </button>

      </div>

    </main>
  );
}