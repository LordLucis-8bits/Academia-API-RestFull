import { getStudentInfo } from "@/lib/getStudentInfo";
import Link from "next/link";

export default async function StudentsPage() {
    const student = await getStudentInfo();

    return (
        <div>
            <header>
                <h1 className="text-2xl font-bold">Welcome, {student?.name}!</h1>
                <p className="text-gray-600">Email: {student?.email}</p>
            </header>
            <div className="grid grid-cols-2 gap-4 mt-8">
                <Link href="/classes/available" className="border rounded p-6 text-center cursor-pointer hover:bg-gray-50">Matricular Aulas</Link>
                <Link href="/students/enrollments" className="border rounded p-6 text-center cursor-pointer hover:bg-gray-50">Minhas Aulas Matriculadas</Link>
                <Link href="/students/plan" className="border rounded p-6 text-center cursor-pointer hover:bg-gray-50">Checar Plano</Link>
                <Link href="/students/me" className="border rounded p-6 text-center cursor-pointer hover:bg-gray-50">Minhas Informações</Link>
            </div> 
        </div>
    );
}
