import { getInstructorInfo } from "@/lib/getInstructorInfo";

export default async function InstructorInfoPage() {
    const instructor = await getInstructorInfo();

    return (
        <div>
            <h1 className="text-2xl font-bold">Instructor Information</h1>
            <p className="text-gray-600">Name: {instructor.name}</p>
            <p className="text-gray-600">Age: {instructor.age}</p>
            <p className="text-gray-600">Email: {instructor.email}</p>
            <p className="text-gray-600">Specialty: {instructor.specialty}</p>
        </div>
    );
}