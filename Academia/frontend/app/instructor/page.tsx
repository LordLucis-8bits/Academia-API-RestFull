import { getInstructorInfo } from "@/lib/getInstructorInfo";
import Link from "next/link";

export default async function InstructorPage() {
    const instructor = await getInstructorInfo();

    return (
        <div>
            <header>
                <h1 className="text-2xl font-bold">Welcome, {instructor.name}!</h1>
                <p className="text-gray-600">Email: {instructor.email}</p>
            </header>
            <div className="grid grid-cols-2 gap-4 mt-8">
                <Link href="/instructors/my-classes" className="border rounded p-6 text-center cursor-pointer hover:bg-gray-50">My Classes</Link>
                <Link href="/instructors/me" className="border rounded p-6 text-center cursor-pointer hover:bg-gray-50">My Info</Link>
            </div> 
        </div>
    );
}