import Navbar from "./Components/Navbar";
import Link from "next/link";
import Image from "next/image";

export default function Home() {
  return (
    <main className="min-h-screen bg-white">

      <Navbar />


      {/* Botón centrado */}
      <div className="flex justify-center mt-10">
        <Link href="/Cuestionario">
          <button className="bg-pink-500 text-white px-10 py-3 rounded-lg shadow-md hover:bg-pink-600">
            Iniciar Cuestionario
          </button>
        </Link>
      
      </div>


      <div className="max-w-9xl mx-auto px-10 py-10 ">

        {/* SECCIÓN 1 */}
        <div className="grid md:grid-cols-1 gap-8 items-center">

          <div>
            <h1 className="text-3xl text-pink-600 font-bold mb-4 ">
              Cáncer de mama
            </h1>

            <p className="text-black text-justify leading-relaxed">
              El cáncer de mama es una enfermedad en la que células de la mama alteradas se 
              multiplican sin control y forman tumores que, de no tratarse, pueden propagarse por 
              todo el cuerpo y causar la muerte. Las células cancerosas comienzan a desarrollarse 
              dentro de los conductos galactóforos o de los lobulillos que producen leche del seno. 
              El cáncer en estadio 0 (in situ) no es letal y se puede detectar en fases tempranas. Las
              células cancerosas se pueden propagar al tejido mamario cercano (invasión), lo que produce 
              nódulos o engrosamiento. El tratamiento se basa en las características de la paciente, 
              el tipo de cáncer y su propagación. El tratamiento consiste en una combinación de cirugía, 
              radioterapia y medicación. Pertenecer al género femenino es el principal factor de riesgo en el 
              caso del cáncer de mama. Aproximadamente, el 99 % de los casos de cáncer de mama afectan a mujeres, 
              y entre el 0,5 % y el 1 % de los casos, a varones. El tratamiento de esa enfermedad en los varones 
              sigue los mismos principios que los que se aplican a las mujeres. Algunos factores aumentan el riesgo 
              de padecer cáncer de mama, entre ellos el envejecimiento, la obesidad, el consumo nocivo de alcohol, 
              los antecedentes familiares de cáncer de mama, el historial de exposición a radiación, el historial 
              reproductivo (como la edad de inicio de los periodos menstruales y la edad en el primer embarazo), 
              el consumo de tabaco y el tratamiento hormonal posterior a la menopausia. Alrededor de la mitad de 
              los casos de cáncer de mama corresponden a mujeres sin factores de riesgo identificables, a excepción 
              del género (mujer) y la edad (más de 40 añ0s).
            </p>
          </div>

          <div className="flex justify-center">
            <Image
              src="/doctor.webp"
              alt="Cancer de mama"
              width={420}
              height={300}
              className="rounded-2xl shadow-xl object-cover"
            />
          </div>

        </div>


        {/* SECCIÓN 2 */}
        <div className="grid md:grid-cols-2 gap-10 items-center mt-12">

          <div className="flex justify-center">
            <Image
              src="/doctora.jpg"
              alt="Mamografía"
              width={420}
              height={300}
              className="rounded-2xl shadow-xl object-cover"
            />
          </div>

          <div>
            <h2 className="text-xl text-pink-600 font-bold mb-4">
              Factores de riesgo y causas
            </h2>

            <p className="text-black text-justify leading-relaxed">
              Algunos factores aumentan el riesgo de padecer cáncer de mama, entre ellos 
              el envejecimiento, la obesidad, el consumo nocivo de alcohol, los antecedentes 
              familiares de cáncer de mama, el historial de exposición a radiación, el historial 
              reproductivo (como la edad de inicio de los periodos menstruales y la edad en el 
              primer embarazo), el consumo de tabaco y el tratamiento hormonal posterior a la 
              menopausia. Aproximadamente la mitad de los casos de cáncer de mama corresponden 
              a mujeres sin factores de riesgo identificables.
            </p>
          </div>

        </div>

      </div>

    </main>
  );
}